package com.qchenmo.a001;

import android.app.Activity;
import android.view.View;

import com.qchenmo.a001.util.ToastUtil;

public class MyClickListener implements View.OnClickListener {

    private Activity mActivity;

    public MyClickListener(Activity activity){
        this.mActivity = activity;

    }


    @Override
    public void onClick(View v) {
        ToastUtil.showMsg(mActivity,"我是外部类实现的点击");

    }
}
