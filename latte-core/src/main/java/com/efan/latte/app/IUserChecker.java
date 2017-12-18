package com.efan.latte.app;

/**
 * Created by Administrator on 2017/12/11.
 */

public interface IUserChecker {
    //有用户信息
    void onSignIn();

    //没有用户信息
    void onNotSignIn();
}
