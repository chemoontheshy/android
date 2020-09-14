package com.qchenmo.a001.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.qchenmo.a001.R;

public class ContainerActivity extends AppCompatActivity {

    //实例化
    private AFragment aFragment;
    private BFragment bFragment;
    private Button btnChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        FindViewByIdClass();
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bFragment==null){
                    bFragment = new BFragment();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,bFragment).commitAllowingStateLoss();
            }
        });

        aFragment = AFragment.newInstance("我是穿过去到title");
        //将Fragment添加到Activity,记得调用commit
        getSupportFragmentManager().beginTransaction().add(R.id.fl_container, aFragment).commitAllowingStateLoss();

    }

    private void FindViewByIdClass() {
        btnChange = findViewById(R.id.btn_change_Fragment);
    }
}
