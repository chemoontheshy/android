package com.qchenmo.a001.jump;

import android.os.Bundle;
import android.widget.TextView;

import com.qchenmo.a001.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BActivity extends AppCompatActivity {

    private TextView mTvGet_A_data;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        mTvGet_A_data = findViewById(R.id.get_A_data);
        Bundle bundle = getIntent().getExtras();
        String name =  bundle.getString("name");
        int id = bundle.getInt("id");
        mTvGet_A_data.setText("name:"+name+" id:"+id);

    }
}
