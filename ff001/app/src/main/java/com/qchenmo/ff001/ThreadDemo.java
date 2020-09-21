package com.qchenmo.ff001;

public class ThreadDemo {
    static {
        System.loadLibrary("native-lib");
        System.loadLibrary("avcodec");
        System.loadLibrary("avformat");
        System.loadLibrary("swscale");
        System.loadLibrary("avutil");
    }


    public native void normalThread();
    public native void mutexThread();

    private OnErrorListener onErrorListener;

    public void setOnErrorListener(OnErrorListener onErrorListener){
        this.onErrorListener = onErrorListener;
    }

    public void OnError(int code,String msg){

        if (onErrorListener !=null){
            onErrorListener.onError(code,msg);
        }

    }

    public interface OnErrorListener{
        void onError(int code,String msg);
    }
}
