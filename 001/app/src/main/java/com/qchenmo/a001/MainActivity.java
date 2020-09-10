package com.qchenmo.a001;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.qchenmo.a001.jump.AActivity;

public class MainActivity extends AppCompatActivity {

    private Button toBtn_Ui,toBtn_Activity, toBtn_Jump;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toBtn_Ui = findViewById(R.id.Ui);
        toBtn_Activity = findViewById(R.id.btn_Activity);
        toBtn_Jump = findViewById(R.id.btn_Jump);
        setListeners();
    }

    private void setListeners(){
        OnClick onClick = new OnClick();
        toBtn_Ui.setOnClickListener(onClick);
        toBtn_Activity.setOnClickListener(onClick);
        toBtn_Jump.setOnClickListener(onClick);
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
                default:
                    throw new IllegalStateException("Unexpected value: " + v.getId());
            }
        }
    }
}
