package com.bnb.grab.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

/**
 * 吐司
 * Created by nobody on 2015/8/19.
 */
public class MyToast {

    private static Toast sToast;
    private static Handler handler = new Handler(Looper.getMainLooper());

    public static void showTextToast(final Context context, final String msg) {
        if (context == null) return;
        if (sToast == null) {
            if (Looper.getMainLooper() == Looper.myLooper()) {
                initToast(context, msg);
            } else {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        initToast(context, msg);
                    }
                });
            }
        }
        //判断当前代码是否是主线程
        if (Looper.myLooper() == Looper.getMainLooper()) {
            sToast.setText(msg);
            sToast.show();
        } else {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    sToast.setText(msg);
                    sToast.show();
                }
            });
        }
    }

    private static void initToast(Context context, String msg) {
        sToast = Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT);
        sToast.setText(msg);
    }

    public static void showSortToast(Context context, String msg) {
        showTextToast(context, msg);

    }
}
