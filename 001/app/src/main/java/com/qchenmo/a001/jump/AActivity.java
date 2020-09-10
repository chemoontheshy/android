package com.qchenmo.a001.jump;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.qchenmo.a001.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AActivity extends AppCompatActivity {

    Button btn_toBactivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);

        btn_toBactivity = findViewById(R.id.btn_toBActivity);
        btn_toBactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //显式跳转1
                Intent intent = new Intent(AActivity.this,BActivity.class);
                intent.putExtra("name","沉默");
                intent.putExtra("id",001);
                startActivity(intent);
                //显式跳转2
//                Intent intent = new Intent();
//                intent.setClass(AActivity.this,BActivity.class);
//                startActivity(intent);
                //显式跳转3
//                Intent intent = new Intent();
//                intent.setClassName(AActivity.this,"com.qchenmo.a001.jump.BActivity");
//                startActivity(intent);
                //显式跳转4
//                Intent intent = new Intent();
//                intent.setComponent(new ComponentName(AActivity.this,"com.qchenmo.a001.jump.BActivity"));
//                startActivity(intent);
                //隐式
//                Intent intent = new Intent();
//                intent.setAction("com.qchenmo.test.BActivity");
//                startActivity(intent);
            }
        });


    }
}
