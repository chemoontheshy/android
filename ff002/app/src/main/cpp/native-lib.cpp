#include <jni.h>
#include <string>
#include <android/native_window_jni.h>
#include <unistd.h>
#include <android/log.h>



#define TAG "test" // 这个是自定义的LOG的标识
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG,TAG ,__VA_ARGS__) // 定义LOGD类型
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,TAG ,__VA_ARGS__) // 定义LOGI类型
#define LOGW(...) __android_log_print(ANDROID_LOG_WARN,TAG ,__VA_ARGS__) // 定义LOGW类型
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,TAG ,__VA_ARGS__) // 定义LOGE类型
#define LOGF(...) __android_log_print(ANDROID_LOG_FATAL,TAG ,__VA_ARGS__) // 定义LOGF类型


bool status = true;

extern "C" {
#include "include/libavcodec/avcodec.h"
#include <libavformat/avformat.h>
#include <libswscale/swscale.h>
#include <libavutil/imgutils.h>
}


extern "C"
JNIEXPORT jstring JNICALL
Java_com_qchenmo_ff002_MainActivity_test(JNIEnv *env, jobject thiz) {
    // TODO: implement test()
    return env->NewStringUTF(avcodec_configuration());
}extern "C"
JNIEXPORT void JNICALL
Java_com_qchenmo_ff002_WangyiPlayer_native_1start(JNIEnv *env, jobject thiz, jstring path,
                                                  jobject surface) {
    // TODO: implement native_start()
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
    rtspURL = "rtsp://192.168.1.100/11";

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
        LOGD("open the url error");
        return;

    }
    //4.寻找输入码流
    ret = avformat_find_stream_info(iFormatCtx, nullptr);
    if (ret < 0) {

        LOGD("Failed to retrieve input stream information.");
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
        LOGD("Codec not fount.");
        return;
    }

    //7.打开解码器
    if (avcodec_open2(iCodecCtx, iCodec, nullptr) < 0) {
        LOGD("Could not open codec,");
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

    int num = 0;

    if(0<ANativeWindow_setBuffersGeometry(nativeWindow,iCodecCtx->width,iCodecCtx->height,WINDOW_FORMAT_RGBA_8888)){
        LOGD("Couldn't set buffers geometry.\n");
        ANativeWindow_release(nativeWindow);
    }
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
                //num++;
                //LOGD("decode is %d\n frame",num);
                if(ANativeWindow_lock(nativeWindow,&windowBuffer, nullptr)<0){
                    LOGD("cannot lock window");
                } else{
                    uint8_t *dst = (uint8_t *)windowBuffer.bits;
                    for(int h = 0;h<iCodecCtx->height;h++){
                        memcpy(dst+h*windowBuffer.stride*4,
                               out_buffer+h*oFrameRGB->linesize[0],oFrameRGB->linesize[0]);
                    }
                    ANativeWindow_unlockAndPost(nativeWindow);
                }



            }

        }
        av_packet_unref(packet);
    }


    av_free(out_buffer);
    av_free(iFrame);
    av_free(oFrameRGB);
    avcodec_close(iCodecCtx);
    avformat_close_input(&iFormatCtx);

    //return ;
}extern "C"
JNIEXPORT void JNICALL
Java_com_qchenmo_ff002_RitaPlayer_native_1start(JNIEnv *env, jobject thiz, jstring path,
                                                jobject surface) {
    // TODO: implement native_start()
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
    rtspURL = "rtsp://192.168.1.100/11";

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
        LOGD("open the url error");
        return;

    }
    //4.寻找输入码流
    ret = avformat_find_stream_info(iFormatCtx, nullptr);
    if (ret < 0) {

        LOGD("Failed to retrieve input stream information.");
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
        LOGD("Codec not fount.");
        return;
    }

    //7.打开解码器
    if (avcodec_open2(iCodecCtx, iCodec, nullptr) < 0) {
        LOGD("Could not open codec,");
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

    //int num = 0;

    if(0<ANativeWindow_setBuffersGeometry(nativeWindow,iCodecCtx->width,iCodecCtx->height,WINDOW_FORMAT_RGBA_8888)){
        LOGD("Couldn't set buffers geometry.\n");
        ANativeWindow_release(nativeWindow);
    }
    while (status) {
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
                //num++;
                //LOGD("decode is %d\n frame",num);
                if(ANativeWindow_lock(nativeWindow,&windowBuffer, nullptr)<0){
                    LOGD("cannot lock window");
                } else{
                    uint8_t *dst = (uint8_t *)windowBuffer.bits;
                    for(int h = 0;h<iCodecCtx->height;h++){
                        memcpy(dst+h*windowBuffer.stride*4,
                               out_buffer+h*oFrameRGB->linesize[0],oFrameRGB->linesize[0]);
                    }
                    ANativeWindow_unlockAndPost(nativeWindow);
                }



            }

        }
        av_packet_unref(packet);
    }


    av_free(out_buffer);
    av_free(iFrame);
    av_free(oFrameRGB);
    avcodec_close(iCodecCtx);
    avformat_close_input(&iFormatCtx);
    LOGD("异常结束");
    if(status== false){
        LOGD("正常关闭");
    }
    //return ;
}extern "C"
JNIEXPORT void JNICALL
Java_com_qchenmo_ff002_RitaPlayer_native_1stop(JNIEnv *env, jobject thiz, jboolean bool_status) {
    // TODO: implement native_stop()
    status = bool_status;
}


//引入ANativeWindow
ANativeWindow *window=0;

extern "C"
JNIEXPORT void JNICALL
Java_com_qchenmo_ff002_EsPlayer_native_1prepare(JNIEnv *env, jobject thiz, jstring data_source) {
    // TODO: implement native_prepare()
    const char *dataSource = env->GetStringUTFChars(data_source,0);
    env->ReleaseStringUTFChars(data_source,dataSource);
}extern "C"
JNIEXPORT void JNICALL
Java_com_qchenmo_ff002_EsPlayer_native_1start(JNIEnv *env, jobject thiz) {
    // TODO: implement native_start()
}extern "C"
JNIEXPORT void JNICALL
Java_com_qchenmo_ff002_EsPlayer_native_1set_1surface(JNIEnv *env, jobject thiz, jobject surface) {
    // TODO: implement native_set_surface()

    //释放掉之前的
    if(window){
        ANativeWindow_release(window);
        window=0;
    }
    //创建新的窗口用于视频显示
    window = ANativeWindow_fromSurface(env,surface);
}