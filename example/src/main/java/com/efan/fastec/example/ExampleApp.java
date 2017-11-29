package com.efan.fastec.example;

import android.app.Application;

import com.efan.latte.app.Latte;

/**
 * Created by Administrator on 2017/11/29.
 */

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this).withApiHost("http://127.0.0.1/").configue();
    }
}
