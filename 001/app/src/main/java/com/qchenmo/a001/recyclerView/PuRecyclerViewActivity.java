package com.qchenmo.a001.recyclerView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import com.qchenmo.a001.R;

public class PuRecyclerViewActivity extends AppCompatActivity {

    private RecyclerView mRvpu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pu_recycler_view);
        mRvpu = findViewById(R.id.rv_pu);

    }
}
