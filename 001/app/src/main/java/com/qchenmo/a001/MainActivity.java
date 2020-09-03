package com.qchenmo.a001;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button btn_toTextView,btn_toButton,btn_toEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_toTextView = findViewById(R.id.toTextView);
        btn_toTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TextViewActivity.class);
                startActivity(intent);
            }
        });
        btn_toButton = findViewById(R.id.toButton);
        btn_toButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ButtonActivity.class);
                startActivity(intent);
            }
        });
        btn_toEditText = findViewById(R.id.toEditText);
        btn_toEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到EditText演示界面
                Intent intent = new Intent(MainActivity.this,EditTextActivity.class);
                startActivity(intent);
            }
        });

    }
}
