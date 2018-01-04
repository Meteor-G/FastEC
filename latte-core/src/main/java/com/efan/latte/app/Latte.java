package com.efan.latte.app;

import android.content.Context;

import android.os.Handler;

/**
 * Created by Administrator on 2017/11/29.
 * 对外工具类
 */

public final class Latte {

    public static Configurator init(Context context) {
        Configurator.getInstance()
                .getLatteConfigs()
                .put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
//       Configurator.getInstance().put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurators() {
        return Configurator.getInstance();
    }

    public static <T> T getConfigurations(Object key) {
        return getConfigurators().getConfiguration(key);
    }

    public static Context getApplication() {
        return getConfigurations(ConfigKeys.APPLICATION_CONTEXT);
    }

    public static Handler getHandler() {
        return getConfigurations(ConfigKeys.HANDLER);
    }

//    public static HashMap<Object, Object> getConfigurations() {
//        return Configurator.getInstance().getLatteConfigs();
//    }
//
//    public static Context getApplication() {
//        return (Context) getConfigurations().get(ConfigKeys.APPLICATION_CONTEXT.name());
//    }
}
