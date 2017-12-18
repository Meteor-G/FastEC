package com.efan.latte.app;

import com.efan.latte.util.storage.LattePreference;

/**
 * Created by Administrator on 2017/12/11.
 */

public class AccountManager {
    private enum SingTag {
        SING_TAG
    }

    //保存用户登录状态，登录后调用
    public static void setSignState(boolean state) {
        LattePreference.setAppFlag(SingTag.SING_TAG.name(), state);
    }

    //判断是否登录
    public static boolean isSignIn() {
        return LattePreference.getAppFlag(SingTag.SING_TAG.name());
    }

    public static void checkAccount(IUserChecker checker) {
        if (isSignIn()) {
            checker.onSignIn();
        } else {
            checker.onNotSignIn();
        }
    }
}
