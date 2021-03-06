package com.qchenmo.a001;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.qchenmo.a001.datastorage.DataStorageActivity;
import com.qchenmo.a001.fragment.ContainerActivity;
import com.qchenmo.a001.jump.AActivity;

public class MainActivity extends AppCompatActivity {

    private Button toBtn_Ui,toBtn_Activity, toBtn_Jump, toBtn_Fragment,toBtn_Event,toBtn_Handler,toBtn_Data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FindViewByIdClass();
        setListeners();
    }

    private void FindViewByIdClass(){
        toBtn_Ui = findViewById(R.id.Ui);
        toBtn_Activity = findViewById(R.id.btn_Activity);
        toBtn_Jump = findViewById(R.id.btn_Jump);
        toBtn_Fragment = findViewById(R.id.btn_Fragment);
        toBtn_Event  = findViewById(R.id.btn_Event);
        toBtn_Handler = findViewById(R.id.btn_Handler);
        toBtn_Data = findViewById(R.id.btn_data);
    }

    private void setListeners(){
        OnClick onClick = new OnClick();
        toBtn_Ui.setOnClickListener(onClick);
        toBtn_Activity.setOnClickListener(onClick);
        toBtn_Jump.setOnClickListener(onClick);
        toBtn_Fragment.setOnClickListener(onClick);
        toBtn_Event.setOnClickListener(onClick);
        toBtn_Handler.setOnClickListener(onClick);
        toBtn_Data.setOnClickListener(onClick);
    }

    private class OnClick implements View.OnClickListener{
        Intent intent;
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.Ui:
                    intent = new Intent(MainActivity.this, UiActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_Activity:
                    intent = new Intent(MainActivity.this, LifeCycleActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_Jump:
                    intent = new Intent(MainActivity.this, AActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_Fragment:
                    intent = new Intent(MainActivity.this, ContainerActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_Event:
                    intent = new Intent(MainActivity.this, EventActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_Handler:
                    intent = new Intent(MainActivity.this, HandlerActivity.class);
                    startActivity(intent);
                    break;
                case R.id.btn_data:
                    intent = new Intent(MainActivity.this, DataStorageActivity.class);
                    startActivity(intent);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + v.getId());
            }
        }
    }
}
