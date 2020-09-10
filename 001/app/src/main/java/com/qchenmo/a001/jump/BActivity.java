package com.qchenmo.a001.jump;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.qchenmo.a001.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BActivity extends AppCompatActivity {

    private TextView mTvGet_A_data;

    private Button mBtnFinish,btn_toAActivity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        Log.d("BActivity","----------onNewIntent-----------");
        Log.d("BActivity","taskId:"+getTaskId()+" ,hash:"+hashCode());
        LogTaskName();
        mTvGet_A_data = findViewById(R.id.get_A_data);
        mBtnFinish = findViewById(R.id.btn_finish);
        btn_toAActivity = findViewById(R.id.btn_BtoA);
        btn_toAActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(BActivity.this,AActivity.class);
                startActivity(intent);
            }
        });
        Bundle bundle = getIntent().getExtras();
        String name =  bundle.getString("name");
        int id = bundle.getInt("id");
        mTvGet_A_data.setText("name:"+name+" id:"+id);

        mBtnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("title","我回来了");
                setResult(AActivity.RESULT_OK,intent);
                finish();

            }
        });



    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("BActivity","----------onCreate-----------");
        Log.d("BActivity","taskId:"+getTaskId()+" ,hash:"+hashCode());
        LogTaskName();
    }

    private void LogTaskName(){
        try {
            ActivityInfo info = getPackageManager().getActivityInfo(getComponentName(), PackageManager.GET_META_DATA);
            Log.d("BActivity",info.taskAffinity);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
