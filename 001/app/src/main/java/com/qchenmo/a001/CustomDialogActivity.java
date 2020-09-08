package com.qchenmo.a001;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.qchenmo.a001.widget.CustomDialog;

public class CustomDialogActivity extends AppCompatActivity {

    private Button mBtnDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_dialog);
        mBtnDialog = findViewById(R.id.btn_custom_dialog);
        mBtnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CustomDialog customDialog = new CustomDialog(CustomDialogActivity.this);
                customDialog.setTitle("提示");
                customDialog.setMessage("确定要删除？");
                customDialog.setCancel("取消", new CustomDialog.IOnCancelListener() {
                    @Override
                    public void onCancel(CustomDialog dialog) {
                        Toast.makeText(CustomDialogActivity.this, "已取消", Toast.LENGTH_LONG).show();
                        customDialog.dismiss();
                    }
                });
                customDialog.setComfirm("确定", new CustomDialog.IOnComfirmListener() {
                    @Override
                    public void onComfirm(CustomDialog dialog) {
                        Toast.makeText(CustomDialogActivity.this, "已删除", Toast.LENGTH_LONG).show();
                        customDialog.dismiss();
                    }
                });
                customDialog.show();
            }
        });

    }
}
