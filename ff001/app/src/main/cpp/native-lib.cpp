#include <jni.h>
#include <android/log.h>
#include <android/native_window.h>
#include <android/native_window_jni.h>
#include <pthread.h>
#include <queue>
#include <unistd.h>

extern "C" {
#include "include/libavcodec/avcodec.h"
#include "include/libavformat/avformat.h"
#include "include/libswscale/swscale.h"
#include "include/libavutil/time.h"
#include "include/libavutil/mathematics.h"
#include "include/libavutil/imgutils.h"
#include "include/libavutil/pixfmt.h"

}

pthread_t thread;

//生产者
pthread_t produce;

//消费者
pthread_t custom;

pthread_mutex_t mutex;

pthread_cond_t cond;

bool isExit = false;


std::queue<int> queue;



#define TAG "native" // 这个是自定义的LOG的标识
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG,TAG ,__VA_ARGS__) // 定义LOGD类型
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,TAG ,__VA_ARGS__) // 定义LOGI类型
#define LOGW(...) __android_log_print(ANDROID_LOG_WARN,TAG ,__VA_ARGS__) // 定义LOGW类型
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,TAG ,__VA_ARGS__) // 定义LOGE类型
#define LOGF(...) __android_log_print(ANDROID_LOG_FATAL,TAG ,__VA_ARGS__) // 定义LOGF类型


void *normalCallBack(void *data){

    LOGD("Create normal thread from C++!");
    pthread_exit(&thread);

}

void *produceCallBack(void *data){
    while (!isExit){
        pthread_mutex_lock(&mutex);
        queue.push(1);
        LOGD("生产者生产一个产品，通知消费者，产品数量为%d",queue.size());
        pthread_cond_signal(&cond);
        pthread_mutex_unlock(&mutex);
        sleep(5);
    }

    pthread_exit(&produce);

}

void *customCallBack(void *data){
    while (!isExit){
        pthread_mutex_lock(&mutex);
        if(queue.size()>0){
            queue.pop();
            LOGD("消费者消费产品，产品数量还剩余%d",queue.size());
        } else{
            LOGD("没有产品可以消费，等待中");
            pthread_cond_wait(&cond,&mutex);

        }
        pthread_mutex_unlock(&mutex);
        usleep(1000*500);
    }
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_qchenmo_ff001_MainActivity_hello(JNIEnv *env, jobject thiz) {
    // TODO: implement hello()
    AVFormatContext *iFormatCtx;
    AVCodecContext *iCodecCtx;
    AVCodec *iCodec;
    AVFrame *iFrame, *oFrameRGB;
    AVPacket *packet;
    uint8_t *out_buffer;
    int videoStream = -1;
    int i, numBytes;
    int ret, got_picture;
    static struct SwsContext *img_convert_ctx;

    //新增
    const char *error;
    //ANativeWindow *nativeWindow = ANativeWindow_fromSurface(env, surface);
    //ANativeWindow_Buffer windowBuffer;


    //赋予结构体
    iFormatCtx = avformat_alloc_context();
    iCodecCtx = avcodec_alloc_context3(nullptr);
    iFrame = av_frame_alloc();
    oFrameRGB = av_frame_alloc();

    //输入地址
    const char *rtspURL;
    rtspURL = "rtsp://192.168.1.103/11";

    //FFmpeg 2.网络配置
    avformat_network_init();
    AVDictionary *avdic = nullptr;
    av_dict_set(&avdic, "rtsp_transport", "tcp", 0);
    av_dict_set(&avdic, "max_delay", "100", 0);

    //新增
    av_dict_set(&avdic, "buffer_size", "1024000", 0);
    av_dict_set(&avdic, "setTimeOut", "3000000", 0);

    //3.打开输入流
    ret = avformat_open_input(&iFormatCtx, rtspURL, nullptr, &avdic);
    if (ret < 0) {
        return env->NewStringUTF("error");

    }
    //4.寻找输入码流
    ret = avformat_find_stream_info(iFormatCtx, nullptr);
    if (ret < 0) {
        return env->NewStringUTF("Failed to retrieve input stream information.");
    }

    //5.循环出视频流

    for (i = 0; i < iFormatCtx->nb_streams; i++) {
        if (iFormatCtx->streams[i]->codecpar->codec_type == AVMEDIA_TYPE_VIDEO) {
            videoStream = i;
            break;
        }
    }

    //6.复制视频流打信息
    avcodec_parameters_to_context(iCodecCtx, iFormatCtx->streams[videoStream]->codecpar);
    iCodec = avcodec_find_decoder(iCodecCtx->codec_id);
    if (iCodec == nullptr) {
        return env->NewStringUTF("Codec not fount.");
    }

    //7.打开解码器
    if (avcodec_open2(iCodecCtx, iCodec, nullptr) < 0) {
        error = "Could not open codec,";
        return env->NewStringUTF(error);
    }

    //8解码
    img_convert_ctx = sws_getContext(iCodecCtx->width, iCodecCtx->height, iCodecCtx->pix_fmt,
                                     iCodecCtx->width, iCodecCtx->height, AV_PIX_FMT_RGBA,
                                     SWS_BICUBIC,
                                     nullptr, nullptr, nullptr);
    numBytes = av_image_get_buffer_size(AV_PIX_FMT_RGBA, iCodecCtx->width, iCodecCtx->height, 1);
    out_buffer = (uint8_t *) av_malloc(numBytes * sizeof(uint8_t));

    av_image_fill_arrays(oFrameRGB->data, oFrameRGB->linesize, out_buffer, AV_PIX_FMT_RGBA,
                         iCodecCtx->width, iCodecCtx->height, 1);
    int y_size = iCodecCtx->width * iCodecCtx->height;
    packet = (AVPacket *) malloc(sizeof(AVPacket));
    av_new_packet(packet, y_size);

    int num = 0;
    while (1) {
        if (av_read_frame(iFormatCtx, packet) < 0) {
            break;
        }
        if (packet->stream_index == videoStream) {
            ret = avcodec_send_packet(iCodecCtx, packet);
            if (ret < 0) {
                break;
            }
            got_picture = avcodec_receive_frame(iCodecCtx, iFrame);
            if (!got_picture) {
                //__android_log_print(ANDROID_LOG_ERROR, "native-log","w");
                sws_scale(img_convert_ctx, (uint8_t const *const *) iFrame->data, iFrame->linesize,
                          0, iCodecCtx->height, oFrameRGB->data, oFrameRGB->linesize);
                num++;
                LOGD("decode is %d\n frame",num);

            }
        }
        av_packet_unref(packet);
    }


    av_free(out_buffer);
    av_free(iFrame);
    av_free(oFrameRGB);
    avcodec_close(iCodecCtx);
    avformat_close_input(&iFormatCtx);

    return env->NewStringUTF("ok");
}

extern "C"
JNIEXPORT void JNICALL
Java_com_qchenmo_ff001_MainActivity_play(JNIEnv *env, jobject thiz, jstring url, jobject surface) {
    // TODO: implement play()
    AVFormatContext *iFormatCtx;
    AVCodecContext *iCodecCtx;
    AVCodec *iCodec;
    AVFrame *iFrame, *oFrameRGB;
    AVPacket *packet;
    uint8_t *out_buffer;
    int videoStream = -1;
    int i, numBytes;
    int ret, got_picture;
    static struct SwsContext *img_convert_ctx;

    //新增
    const char *error;
    ANativeWindow *nativeWindow = ANativeWindow_fromSurface(env, surface);
    ANativeWindow_Buffer windowBuffer;


    //赋予结构体
    iFormatCtx = avformat_alloc_context();
    iCodecCtx = avcodec_alloc_context3(nullptr);
    iFrame = av_frame_alloc();
    oFrameRGB = av_frame_alloc();

    //输入地址
    const char *rtspURL;
    rtspURL = "rtsp://192.168.1.103/11";

    //FFmpeg 2.网络配置
    avformat_network_init();
    AVDictionary *avdic = nullptr;
    av_dict_set(&avdic, "rtsp_transport", "tcp", 0);
    av_dict_set(&avdic, "max_delay", "100", 0);

    //新增
    av_dict_set(&avdic, "buffer_size", "1024000", 0);
    av_dict_set(&avdic, "setTimeOut", "3000000", 0);

    //3.打开输入流
    ret = avformat_open_input(&iFormatCtx, rtspURL, nullptr, &avdic);
    if (ret < 0) {
        __android_log_print(ANDROID_LOG_INFO, "native-log", "Could not open the RTSP URL");
        return;
    }

    //4.寻找输入码流
    ret = avformat_find_stream_info(iFormatCtx, nullptr);
    if (ret < 0) {
         error= "Failed to retrieve input stream information.";
         LOGD("error: %s\n",error);
        return;
    }

    //5.循环出视频流

    for (i = 0; i < iFormatCtx->nb_streams; i++) {
        if (iFormatCtx->streams[i]->codecpar->codec_type == AVMEDIA_TYPE_VIDEO) {
            videoStream = i;
            break;
        }
    }

    //6.复制视频流打信息
    avcodec_parameters_to_context(iCodecCtx, iFormatCtx->streams[videoStream]->codecpar);
    iCodec = avcodec_find_decoder(iCodecCtx->codec_id);
    if (iCodec == nullptr) {
        error = "iCodec not found";
        LOGD("error: %s\n",error);
        return;
    }

    //7.打开解码器
    if (avcodec_open2(iCodecCtx, iCodec, nullptr) < 0) {
        error = "Could not open codec,";
        LOGD("error: %s\n",error);
        return;
    }

    //8解码
    img_convert_ctx = sws_getContext(iCodecCtx->width, iCodecCtx->height, iCodecCtx->pix_fmt,
                                     iCodecCtx->width, iCodecCtx->height, AV_PIX_FMT_RGBA,
                                     SWS_BICUBIC,
                                     nullptr, nullptr, nullptr);
    numBytes = av_image_get_buffer_size(AV_PIX_FMT_RGBA, iCodecCtx->width, iCodecCtx->height, 1);
    out_buffer = (uint8_t *) av_malloc(numBytes * sizeof(uint8_t));

    av_image_fill_arrays(oFrameRGB->data, oFrameRGB->linesize, out_buffer, AV_PIX_FMT_RGBA,
                         iCodecCtx->width, iCodecCtx->height, 1);
    int y_size = iCodecCtx->width * iCodecCtx->height;
    packet = (AVPacket *) malloc(sizeof(AVPacket));
    av_new_packet(packet, y_size);

    if (0 > ANativeWindow_setBuffersGeometry(nativeWindow, iCodecCtx->width, iCodecCtx->height,
                                             WINDOW_FORMAT_RGBA_8888)) {
        ANativeWindow_release(nativeWindow);
        return;
    }
    int num = 0;
    while (1) {
        num++;
        if (av_read_frame(iFormatCtx, packet) < 0) {
            break;
        }
        if (packet->stream_index == videoStream) {
            ret = avcodec_send_packet(iCodecCtx, packet);
            if (ret < 0) {
                break;
            }
            got_picture = avcodec_receive_frame(iCodecCtx, iFrame);
            if (!got_picture) {
                sws_scale(img_convert_ctx, (uint8_t const *const *) iFrame->data, iFrame->linesize,
                          0, iCodecCtx->height, oFrameRGB->data, oFrameRGB->linesize);
                LOGD("decode is %s\n frame",num);
//                if (ANativeWindow_lock(nativeWindow,&windowBuffer, nullptr)<0){
//                    LOGD("cannot lock window");
//                } else{
//                    uint8_t *dst = (uint8_t *)windowBuffer.bits;
//                    for(int h =0;h<iCodecCtx->height;h++){
//                        memcpy(dst+h*windowBuffer.stride*4,out_buffer+h+oFrameRGB->linesize[0],oFrameRGB->linesize[0]);
//                    }
//                    ANativeWindow_unlockAndPost(nativeWindow);
//                }


            }
        }
        av_packet_unref(packet);
    }
    av_free(out_buffer);
    av_free(iFrame);
    av_free(oFrameRGB);
    avcodec_close(iCodecCtx);
    avformat_close_input(&iFormatCtx);
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_qchenmo_ff001_MainActivity_log(JNIEnv *env, jobject thiz) {
    // TODO: implement log()
    const char *error = "I am a log test";
    LOGD("this is %s\n",error);
}extern "C"
JNIEXPORT void JNICALL
Java_com_qchenmo_ff001_ThreadDemo_normalThread(JNIEnv *env, jobject thiz) {
    // TODO: implement normalThread()
    pthread_create(&thread, nullptr, normalCallBack, nullptr);
}extern "C"
JNIEXPORT void JNICALL
Java_com_qchenmo_ff001_ThreadDemo_mutexThread(JNIEnv *env, jobject thiz) {
    // TODO: implement mutexThread()

    for (int i = 0; i <10 ; ++i) {

        queue.push(i);

    }

    pthread_mutex_init(&mutex, nullptr);
    pthread_cond_init(&cond, nullptr);
    pthread_create(&produce, nullptr, produceCallBack, nullptr);
    pthread_create(&custom, nullptr,customCallBack, nullptr);
}