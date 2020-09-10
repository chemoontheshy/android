package com.qchenmo.a001.jump;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.qchenmo.a001.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AActivity extends AppCompatActivity {

    Button btn_toBActivity,btn_AtoA;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);

        Log.d("AActivity","----------onCreate-----------");
        Log.d("AActivity","taskId:"+getTaskId()+" ,hash:"+hashCode());
        LogTaskName();
        btn_toBActivity = findViewById(R.id.btn_toBActivity);
        btn_AtoA = findViewById(R.id.btn_toAActivity);
        btn_AtoA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AActivity.this,AActivity.class);
                startActivity(intent);
            }
        });
        btn_toBActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //显式跳转1
                Intent intent = new Intent(AActivity.this, BActivity.class);
                intent.putExtra("name", "沉默");
                intent.putExtra("id", 001);
                startActivity(intent);
                //startActivityForResult(intent,0);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(AActivity.this, data.getExtras().getString("title"), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("AActivity","----------onNewIntent-----------");
        Log.d("AActivity","taskId:"+getTaskId()+" ,hash:"+hashCode());
        LogTaskName();
    }

    private void LogTaskName(){
        try {
            ActivityInfo info = getPackageManager().getActivityInfo(getComponentName(), PackageManager.GET_META_DATA);
            Log.d("AActivity",info.taskAffinity);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
