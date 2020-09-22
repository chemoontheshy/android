package com.qchenmo.ff001;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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
    Button btn_play,btn_create_Thread,btn_mutexThread,btn_callJAVA;
    TextView textView;

    private ThreadDemo threadDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findByIdClass();
        setListenerClase();
        initClass();


    }
    private void initClass(){
        threadDemo = new ThreadDemo();
        threadDemo.setOnErrorListener(new ThreadDemo.OnErrorListener() {
            @Override
            public void onError(int code, String msg) {
                Log.d("onError","code is : "+code+"msg is :"+msg);
            }
        });
    }

    private void findByIdClass(){
        btn_play = findViewById(R.id.btn_play);
        btn_create_Thread = findViewById(R.id.btn_create);
        btn_mutexThread = findViewById(R.id.btn_mutexThread);
        btn_callJAVA = findViewById(R.id.btn_callJAVA);

        textView = findViewById(R.id.tv_show);
        surfaceView = findViewById(R.id.surface);


    }

    private void setListenerClase(){
        OnClick onClick = new OnClick();
        btn_play.setOnClickListener(onClick);
        btn_create_Thread.setOnClickListener(onClick);
        btn_mutexThread.setOnClickListener(onClick);
        btn_callJAVA.setOnClickListener(onClick);
    }

    private class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_play:
                    hello();
                    break;
                case R.id.btn_create:
                    threadDemo.normalThread();
                    break;
                case R.id.btn_mutexThread:
                    threadDemo.mutexThread();
                case R.id.btn_callJAVA:
                    threadDemo.CallBackFromC();
            }
        }
    }




    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String log();
    public native String hello();

    public native void play(String url, Surface surface);

}
