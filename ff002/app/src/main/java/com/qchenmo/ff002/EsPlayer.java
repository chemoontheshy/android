package com.qchenmo.ff002;

import android.view.Surface;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

public class EsPlayer implements SurfaceHolder.Callback {
    static {
        System.loadLibrary("native-lib");
    }

    private String dataSource;

    private native void native_prepare(String dataSource);
    private native void native_start();
    private native void native_set_surface(Surface surface);

    private SurfaceHolder surfaceHolder;

    public void setSurfaceView(SurfaceView surfaceView){
        if(null != this.surfaceHolder){
            this.surfaceHolder.removeCallback(this);
        }
        this.surfaceHolder = surfaceView.getHolder();
        this.surfaceHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        native_set_surface(surfaceHolder.getSurface());
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public void prepare() {
        native_prepare(dataSource);
    }

    public void setDataSource(String absolutePath) {
        this.dataSource = absolutePath;
    }
}
