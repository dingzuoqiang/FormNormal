package com.dzq.formnormal.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by dingzuoqiang on 17-04-24.
 */
public class ToastTools {
    final static String TAG = "ToastTools";

    private Toast toast = null;
    private static ToastTools sToastTools = new ToastTools();


    public static synchronized void showToast(Context context, String content) {
        cancelToast();
        if (sToastTools.toast == null) {
            sToastTools.toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
            sToastTools.toast.setGravity(Gravity.CENTER, 0, 0);
            sToastTools.toast.show();
        }
    }

    public static synchronized void cancelToast() {
        if (null != sToastTools.toast) {
            sToastTools.toast.cancel();
        }
        sToastTools.toast = null;
    }

}
