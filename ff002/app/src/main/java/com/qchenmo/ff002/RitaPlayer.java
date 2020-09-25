package com.qchenmo.ff002;

import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class RitaPlayer  implements SurfaceHolder.Callback {
    static {
        System.loadLibrary("native-lib");
    }

    private SurfaceHolder surfaceHolder;

    private boolean bool_status = false;


    public void setSurfaceHolder(SurfaceView surfaceView){
        if(null!=this.surfaceHolder){
            this.surfaceHolder.removeCallback(this);
        }
        this.surfaceHolder = surfaceView.getHolder();
        this.surfaceHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    //播放
    public void start(String path){
        native_start(path,surfaceHolder.getSurface());
    }
    //停止播放
    public void stop() {
        native_stop(bool_status);
    }

    public native void native_stop(boolean bool_status);

    //
    //播放
    public native void native_start(String path, Surface surface);


}
