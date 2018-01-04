package com.efan.fastec.example;

import android.app.Application;

import com.efan.ec.icon.FontEcModule;
import com.efan.latte.app.Latte;
import com.efan.latte.net.Interceptors.DebugInterceptor;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by Administrator on 2017/11/29.
 */

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
//                .withApiHost("http://101.200.58.242:8080/test/api/")
                .withApiHost("http://10.99.19.137:8080/FastEC/")
                .withInterceptor(new DebugInterceptor("index", R.raw.test))
//                .withWeChatAppId("")
//                .withWeChatAppSecret("")
                .configue();
//        DataBaseManager.getInstance().init(this);
    }
}
