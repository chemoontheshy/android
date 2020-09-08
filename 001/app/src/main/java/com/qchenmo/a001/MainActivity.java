package com.qchenmo.a001;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button toBtn_Ui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toBtn_Ui = findViewById(R.id.Ui);
        setListeners();
    }

    private void setListeners(){
        OnClick onclick = new OnClick();
        toBtn_Ui.setOnClickListener(onclick);
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
                default:
                    throw new IllegalStateException("Unexpected value: " + v.getId());
            }
        }
    }
}
