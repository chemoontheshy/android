package com.qchenmo.a001;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qchenmo.a001.util.ToastUtil;

public class ToastActivity extends AppCompatActivity {

    private Button mBtnToast_1,mBtnToast_2,mBtnToast_3,mBtnToast_4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
        mBtnToast_1 = findViewById(R.id.btn_toast_1);
        mBtnToast_2 = findViewById(R.id.btn_toast_2);
        mBtnToast_3 = findViewById(R.id.btn_toast_3);
        mBtnToast_4 = findViewById(R.id.btn_toast_4);

        OnClick onClick = new OnClick();
        mBtnToast_1.setOnClickListener(onClick);
        mBtnToast_2.setOnClickListener(onClick);
        mBtnToast_3.setOnClickListener(onClick);
        mBtnToast_4.setOnClickListener(onClick);
    }

    class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.btn_toast_1:
                    //默认
                    Toast.makeText(ToastActivity.this,"我是默认的toast",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_toast_2:
                    //居中
                    Toast toastCenter = Toast.makeText(getApplicationContext(),"居中Toast",Toast.LENGTH_SHORT);
                    toastCenter.setGravity(Gravity.CENTER,0,0);
                    toastCenter.show();

                    break;
                case R.id.btn_toast_3:
                    //自定义
                    Toast toastCustom = new Toast(getApplicationContext());
                    LayoutInflater inflater = LayoutInflater.from(ToastActivity.this);
                    View view = inflater.inflate(R.layout.layout_toast,null);
                    ImageView imageView = view.findViewById(R.id.iv_toast);
                    TextView textView = view.findViewById(R.id.tv_toast);
                    imageView.setImageResource(R.drawable.bg_z_logo);
                    textView.setText("自定义Toast");
                    toastCustom.setView(view);
                    toastCustom.show();
                    break;
                case R.id.btn_toast_4:
                    //Util
                    ToastUtil.showMsg(getApplicationContext(),"包装过的toast");
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + v.getId());
            }

        }
    }
}
