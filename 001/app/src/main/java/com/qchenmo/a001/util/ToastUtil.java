package com.qchenmo.a001.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {

    public static Toast mToast;
    public static void showMsg(Context context, String mgs){
        if(mToast == null){
            mToast = Toast.makeText(context,mgs,Toast.LENGTH_SHORT);
        }else {
            mToast.setText(mgs);
        }
        mToast.show();
    }

}
