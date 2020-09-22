package com.qchenmo.ff002;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SurfaceView surfaceView;
    WangyiPlayer wangyiPlayer;
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_main);
        surfaceView = findViewById(R.id.surfaceView);

        wangyiPlayer = new WangyiPlayer();

        wangyiPlayer.setSurfaceHolder(surfaceView);


    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String test();

    public void play(View view) {

        String absolutePath = "rtsp://192.168.1.106/11";
        wangyiPlayer.start(absolutePath);
    }
}
