package com.qchenmo.a001;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ButtonActivity extends AppCompatActivity {

    private Button btn_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        btn_3 = findViewById(R.id.btn_3);
        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //常用方法
                Toast.makeText(ButtonActivity.this,"我是showToast",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showToast(View view){
        //非常用的
        Toast.makeText(this,"我是showToast",Toast.LENGTH_SHORT).show();
    }
}
