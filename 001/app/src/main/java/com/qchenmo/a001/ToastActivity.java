package com.qchenmo.a001;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

public class ToastActivity extends AppCompatActivity {

    private Button mBtnToast_1,mBtnToast_2,mBtnToast_3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
        mBtnToast_1 = findViewById(R.id.btn_toast_1);
        mBtnToast_2 = findViewById(R.id.btn_toast_2);
        mBtnToast_3 = findViewById(R.id.btn_toast_3);

        OnClick onClick = new OnClick();
        mBtnToast_1.setOnClickListener(onClick);
        mBtnToast_2.setOnClickListener(onClick);
        mBtnToast_3.setOnClickListener(onClick);
    }

    class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.btn_toast_1:
                    Toast.makeText(ToastActivity.this,"我是默认的toast",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_toast_2:
                    //居中
                    Toast toastCenter = Toast.makeText(getApplicationContext(),"居中Toast",Toast.LENGTH_SHORT);
                    toastCenter.setGravity(Gravity.CENTER,0,0);
                    toastCenter.show();

                    break;
                case R.id.btn_toast_3:
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + v.getId());
            }

        }
    }
}
