package com.qchenmo.a001.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.qchenmo.a001.R;

import androidx.annotation.NonNull;

public class CustomDialog extends Dialog implements View.OnClickListener {

    private TextView mTvTitle,mTvMessage,mTvCancel,mTvComfirm;

    private String title,message,cancel,comfirm;

    private IOnCancelListener cancelListener;

    private IOnComfirmListener comfirmListener;


    public CustomDialog(@NonNull Context context) {
        super(context);
    }
    public CustomDialog(@NonNull Context context,int themeId) {
        super(context,themeId);
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCancel(String cancel, IOnCancelListener listener) {
        this.cancel = cancel;
        this.cancelListener = listener;
    }

    public void setComfirm(String comfirm,IOnComfirmListener listener) {
        this.comfirm = comfirm;
        this.comfirmListener = listener;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_custom_dialog);
        //设置宽度

        mTvTitle = findViewById(R.id.custom_tip);
        mTvMessage = findViewById(R.id.custom_message);
        mTvCancel = findViewById(R.id.custom_cancel);
        mTvComfirm = findViewById(R.id.custom_confirm);

        if(!TextUtils.isEmpty(title)){
            mTvTitle.setText(title);
        }
        if(!TextUtils.isEmpty(message)){
            mTvMessage.setText(message);
        }
        if(!TextUtils.isEmpty(cancel)){
            mTvCancel.setText(cancel);
        }
        if(!TextUtils.isEmpty(comfirm)){
            mTvComfirm.setText(comfirm);
        }
        mTvCancel.setOnClickListener(this);
        mTvComfirm.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.custom_cancel:
                if(cancelListener!=null){
                    cancelListener.onCancel(this);
                }
                break;
            case R.id.custom_confirm:
                if(comfirmListener!=null){
                    comfirmListener.onComfirm(this);
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }

    public interface IOnCancelListener {
        void onCancel(CustomDialog dialog);
    }
    public interface IOnComfirmListener{
        void onComfirm(CustomDialog dialog);
    }
}
