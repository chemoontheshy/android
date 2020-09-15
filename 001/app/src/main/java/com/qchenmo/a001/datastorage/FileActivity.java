package com.qchenmo.a001.datastorage;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.qchenmo.a001.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;

public class FileActivity extends AppCompatActivity {

    private EditText getEtMsg;
    private Button getBtnSave, getBtnShow;
    private TextView getTvShow;

    private final String mFilename = "text.txt";


    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        FindViewByIdClass();
        ListenersClass();

    }


    private void FindViewByIdClass() {
        getEtMsg = findViewById(R.id.et_file_msg);
        getBtnSave = findViewById(R.id.btn_file_save);
        getBtnShow = findViewById(R.id.btn_file_show);
        getTvShow = findViewById(R.id.tv_file_show);
    }

    private void ListenersClass() {
        OnClick onClick = new OnClick();
        getBtnSave.setOnClickListener(onClick);
        getBtnShow.setOnClickListener(onClick);
    }


    private class OnClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_file_save:
                    Log.d("save", "已保存");
                    fileSave(getEtMsg.getText().toString().trim());
                    break;
                case R.id.btn_file_show:
                    getTvShow.setText(fileRead());
                    Log.d("show", "已显示");
                    break;
            }
        }
    }

    private void fileSave(String content) {


        FileOutputStream fileOutputStream = null;
        try {
            //fileOutputStream = openFileOutput(mFilename, MODE_PRIVATE);
            File dir = new File(getExternalFilesDir(null),"a001");
            if(!dir.exists()){
                dir.mkdirs();
            }
            File file = new File(dir,mFilename);

            if(!file.exists()){
                file.createNewFile();
            }
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(content.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private String fileRead() {

        FileInputStream fileInputStream=null;
        try {
            //fileInputStream = openFileInput(mFilename);
            File file = new File(getExternalFilesDir(null).getAbsolutePath()+File.separator+"a001",mFilename);
            fileInputStream = new FileInputStream(file);
            byte[] buff = new byte[1024];
            StringBuilder sb = new StringBuilder();
            int len;
            while ((len = fileInputStream.read(buff))>0){
                sb.append(new String(buff,0,len));

            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
           if(fileInputStream != null){
               try {
                   fileInputStream.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
        }

        return null;

    }
}
