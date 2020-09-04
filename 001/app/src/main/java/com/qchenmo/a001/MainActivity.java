package com.qchenmo.a001;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;

import com.qchenmo.a001.gridview.GridViewActivity;
import com.qchenmo.a001.listview.ListViewActivity;

public class MainActivity extends AppCompatActivity {

    private Button btn_toTextView,btn_toButton,btn_toEditText,btn_toRadio,btn_toCheckBox,btn_toImageView,btn_toListView,btn_toGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //功能界面
        btn_toTextView = findViewById(R.id.toTextView);
        btn_toButton = findViewById(R.id.toButton);
        btn_toEditText = findViewById(R.id.toEditText);
        btn_toRadio = findViewById(R.id.toRadio);
        btn_toCheckBox = findViewById(R.id.toCheckBox);
        btn_toImageView = findViewById(R.id.toImageView);
        btn_toListView = findViewById(R.id.toListView);
        btn_toGridView = findViewById(R.id.toGridView);
        setListeners();
    }

    private void setListeners(){
        Onclick onclick = new Onclick();
        btn_toTextView.setOnClickListener(onclick);
        btn_toButton.setOnClickListener(onclick);
        btn_toEditText.setOnClickListener(onclick);
        btn_toRadio.setOnClickListener(onclick);
        btn_toCheckBox.setOnClickListener(onclick);
        btn_toImageView.setOnClickListener(onclick);
        btn_toListView.setOnClickListener(onclick);
    }

    private class Onclick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()){

                case R.id.toTextView:
                    //跳转到Text演示界面
                    intent = new Intent(MainActivity.this,TextViewActivity.class);
                    break;
                case R.id.toButton:
                    //跳转到Button演示界面
                    intent = new Intent(MainActivity.this,ButtonActivity.class);
                    break;
                case R.id.toEditText:
                    //跳转到EditText演示界面
                    intent = new Intent(MainActivity.this,EditTextActivity.class);
                    break;
                case R.id.toRadio:
                    //跳转到Radio演示界面
                    intent = new Intent(MainActivity.this,RadioActivity.class);
                    break;
                case R.id.toCheckBox:
                    //跳转到Radio演示界面
                    intent = new Intent(MainActivity.this,CheckBoxActivity.class);
                    break;
                case R.id.toImageView:
                    //跳转到Radio演示界面
                    intent = new Intent(MainActivity.this,ImageViewActivity.class);
                    break;
                case R.id.toListView:
                    //跳转到Radio演示界面
                    intent = new Intent(MainActivity.this, ListViewActivity.class);
                    break;
                case R.id.toGridView:
                    //跳转到Radio演示界面
                    intent = new Intent(MainActivity.this, GridViewActivity.class);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + v.getId());
            }
            startActivity(intent);
        }
    }

}
