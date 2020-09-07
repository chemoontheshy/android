package com.qchenmo.a001.recyclerView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.qchenmo.a001.R;

public class RecyclerViewActivity extends AppCompatActivity {

    private Button btn_toLinearView,btn_toHorizontalView,btn_toPuRecyclerVinw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        btn_toLinearView = findViewById(R.id.toLinearView);
        btn_toLinearView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecyclerViewActivity.this,LinearRecyclerViewActivity.class);
                startActivity(intent);
            }
        });
        btn_toHorizontalView = findViewById(R.id.toHorizontalView);
        btn_toHorizontalView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecyclerViewActivity.this,HorRecyclerViewActivity.class);
                startActivity(intent);
            }
        });
        btn_toPuRecyclerVinw = findViewById(R.id.toPuRecyclerView);
        btn_toPuRecyclerVinw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecyclerViewActivity.this,PuRecyclerViewActivity.class);
                startActivity(intent);
            }
        });

    }
}
