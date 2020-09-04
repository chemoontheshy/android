package com.qchenmo.a001.listview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.qchenmo.a001.R;

import androidx.annotation.Nullable;

public class ListViewActivity extends Activity {

    private ListView mListView1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        mListView1  = findViewById(R.id.lv_1);
        mListView1.setAdapter(new MyListAdapter(ListViewActivity.this));
        mListView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListViewActivity.this,"点击pos:"+position,Toast.LENGTH_SHORT).show();
            }
        });
        mListView1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListViewActivity.this,"长按点击pos:"+position,Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}
