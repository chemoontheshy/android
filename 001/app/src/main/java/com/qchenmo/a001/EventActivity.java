package com.qchenmo.a001;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.qchenmo.a001.util.ToastUtil;
import com.qchenmo.a001.widget.MyButton;

public class EventActivity extends AppCompatActivity {


    private Button mBtnClickEvent;

    private MyButton myButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        //内部类实现
        mBtnClickEvent = findViewById(R.id.btn_clickEvent);
        myButton = findViewById(R.id.btn_MyBtn);

        myButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        Log.d("Listener","--onTouch---");
                        break;
                }
                return false;
            }
        });

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Listener","--onClick---");
            }
        });

        //内部类实现
        //mBtnClickEvent.setOnClickListener(new Onclick());
        //匿名内部类
//        mBtnClickEvent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ToastUtil.showMsg(EventActivity.this,"我是匿名内部类实现的点击");
//            }
//        });
        //通过事件源所在的类实现
        //mBtnClickEvent.setOnClickListener((View.OnClickListener) EventActivity.this);
        //通过外部类
        mBtnClickEvent.setOnClickListener(new MyClickListener(EventActivity.this));
    }

    private void Onclick(View v){
        switch (v.getId()){
            case R.id.btn_clickEvent:
                ToastUtil.showMsg(EventActivity.this,"");
                break;
        }
    }

    private class Onclick implements View.OnClickListener{

        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.btn_clickEvent:
                    ToastUtil.showMsg(EventActivity.this,"我是内部类实现的点击");
                    break;
            }

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d("Activity","--onTouchEvent---");
                break;
        }
        return false;
    }
}
