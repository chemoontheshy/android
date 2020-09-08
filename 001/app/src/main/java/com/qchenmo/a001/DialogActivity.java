package com.qchenmo.a001;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.qchenmo.a001.util.ToastUtil;

public class DialogActivity extends AppCompatActivity {

    private Button mBtnDialog1, mBtnDialog2, mBtnDialog3, mBtnDialog4,mBtnDialog5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        findByIdClass();
        setListeners();
    }

    private void findByIdClass() {
        mBtnDialog1 = findViewById(R.id.btn_dialog1);
        mBtnDialog2 = findViewById(R.id.btn_dialog2);
        mBtnDialog3 = findViewById(R.id.btn_dialog3);
        mBtnDialog4 = findViewById(R.id.btn_dialog4);
        mBtnDialog5 = findViewById(R.id.btn_dialog5);
    }

    private void setListeners() {
        OnClick onClick = new OnClick();
        mBtnDialog1.setOnClickListener(onClick);
        mBtnDialog2.setOnClickListener(onClick);
        mBtnDialog3.setOnClickListener(onClick);
        mBtnDialog4.setOnClickListener(onClick);
        mBtnDialog5.setOnClickListener(onClick);
    }

    class OnClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_dialog1:
                    final AlertDialog.Builder builder = new AlertDialog.Builder(DialogActivity.this);
                    //标题
                    builder.setTitle("请回答");
                    //问题
                    builder.setMessage("如何快速学会安卓开发？");
                    //好
                    builder.setPositiveButton("不学立减100%烦恼", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ToastUtil.showMsg(DialogActivity.this,"滚");
                        }
                    });
                    //一般
                    builder.setNeutralButton("你问谁呢？", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ToastUtil.showMsg(DialogActivity.this,"你");
                        }
                    });
                    //不好
                    builder.setNegativeButton("劝人学Java，天打雷劈", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ToastUtil.showMsg(DialogActivity.this,"???");
                        }
                    });
                    //最后
                    builder.show();
                    break;
                case R.id.btn_dialog2:
                    final String[] array = new String[]{"男","女"};
                    final AlertDialog.Builder builder2 = new AlertDialog.Builder(DialogActivity.this);
                    builder2.setTitle("选择性别");
                    builder2.setItems(array, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ToastUtil.showMsg(DialogActivity.this,array[which]);
                            builder2.show();
                        }
                    });
                    builder2.show();
                    break;
                case R.id.btn_dialog3:
                    final String[] array3 = new String[]{"男","女"};
                    final AlertDialog.Builder builder3 = new AlertDialog.Builder(DialogActivity.this);
                    builder3.setTitle("选择性别");
                    builder3.setSingleChoiceItems(array3, 0, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ToastUtil.showMsg(DialogActivity.this,array3[which]);
                            dialog.dismiss();
                        }
                    });
                    builder3.setCancelable(false);
                    builder3.show();

                    break;
                case R.id.btn_dialog4:
                    final String[] array4 = new String[]{"看番","rap","唱歌","打篮球"};
                    boolean[] isSelected = new boolean[]{false,false,true,false};
                    final AlertDialog.Builder builder4 = new AlertDialog.Builder(DialogActivity.this);
                    builder4.setTitle("选择兴趣？");
                    builder4.setMultiChoiceItems(array4, isSelected, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                            ToastUtil.showMsg(DialogActivity.this,array4[which]+":"+isChecked);
                            //dialog.dismiss();
                        }
                    });
                    //确定
                    builder4.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    //取消
                    builder4.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder4.show();
                    break;
                case R.id.btn_dialog5:
                    //省略
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + v.getId());
            }
        }
    }
}
