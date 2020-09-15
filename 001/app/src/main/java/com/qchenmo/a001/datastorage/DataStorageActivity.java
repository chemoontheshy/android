package com.qchenmo.a001.datastorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.qchenmo.a001.R;

import java.io.File;

public class DataStorageActivity extends AppCompatActivity {

    private Button mBtnSharedPreferences,mBtnFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_storage);
        FindViewByIdClass();
        ListenersClass();
    }

    //寻找id
    private void FindViewByIdClass() {
        mBtnSharedPreferences = findViewById(R.id.btn_SharedPreferences);
        mBtnFile = findViewById(R.id.btn_File);
    }

    //监听
    private void ListenersClass() {
        OnClick onClick = new OnClick();
        mBtnSharedPreferences.setOnClickListener(onClick);
        mBtnFile.setOnClickListener(onClick);
    }

    //按钮事件

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()) {
                case R.id.btn_SharedPreferences:

                    intent = new Intent(DataStorageActivity.this, SharedPreferencesActivity.class);
                    break;
                case R.id.btn_File:
                    intent = new Intent(DataStorageActivity.this, FileActivity.class);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + v.getId());
            }
            startActivity(intent);
        }

    }
}
