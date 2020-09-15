package com.qchenmo.a001.datastorage;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.qchenmo.a001.R;

public class SharedPreferencesActivity extends AppCompatActivity {

    private EditText getEtMsg;
    private Button getBtnSave,getBtnShow;
    private TextView getTvShow;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);
        FindViewByidClass();
        ListenersClass();
        mSharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }


    private void FindViewByidClass(){
        getEtMsg = findViewById(R.id.et_data_msg);
        getBtnSave = findViewById(R.id.btn_data_save);
        getBtnShow = findViewById(R.id.btn_data_show);
        getTvShow = findViewById(R.id.tv_data_show);
    }

    private void ListenersClass(){
        OnClick onClick = new OnClick();
        getBtnSave.setOnClickListener(onClick);
        getBtnShow.setOnClickListener(onClick);
    }



    private class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_data_save:
                    mEditor.putString("name",getEtMsg.getText().toString());
                    mEditor.apply();
                    Log.d("save","已保存");
                    break;
                case R.id.btn_data_show:
                    getTvShow.setText(mSharedPreferences.getString("name",""));
                    Log.d("show","已显示");
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + v.getId());
            }
        }
    }
}
