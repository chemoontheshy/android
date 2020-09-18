package com.qchenmo.ff001;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
        System.loadLibrary("avcodec");
        System.loadLibrary("avformat");
        System.loadLibrary("swscale");
        System.loadLibrary("avutil");
    }

    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    Button btn_play;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hello();
            }
        });
        //textView.setText(hello());
//        surfaceHolder = surfaceView.getHolder();
//        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
//            @Override
//            public void surfaceCreated(SurfaceHolder holder) {
//                String inputUrl = "rtsp://192.168.1.103/11";
//                play(inputUrl,surfaceHolder.getSurface());
//            }
//
//            @Override
//            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//
//            }
//
//            @Override
//            public void surfaceDestroyed(SurfaceHolder holder) {
//
//            }
//        });



    }
    private void initView(){
        btn_play = findViewById(R.id.btn_play);
        textView = findViewById(R.id.tv_show);
        surfaceView = findViewById(R.id.surface);
    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String log();
    public native String hello();

    public native void play(String url, Surface surface);

}
