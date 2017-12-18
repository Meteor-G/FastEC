package com.efan.ec.sign;

import com.efan.latte.app.AccountManager;
import com.efan.latte.util.storage.LattePreference;

/**
 * Created by Administrator on 2017/12/10.
 */

public class SignHandler {

    public static void onSignIn(String request, ISignListener signListener) {
//        final JSONObject profileJson = JSON.parseObject(request).getJSONObject("data");
//        final long userId = profileJson.getLong("usetId");
//        final String name = profileJson.getString("name");
//        final String avatar = profileJson.getString("avatar");
//        final String gender = profileJson.getString("gender");
//        final String address = profileJson.getString("address");

        LattePreference.addCustomAppProfile("usetId", "1");
        LattePreference.addCustomAppProfile("name", "123");
        LattePreference.addCustomAppProfile("avatar", "http://wx.qlogo.cn/mmopen/guWqj0vybsIHxY2BIqqI3iaSHcbWZXiaSQysU0JKwmqjqMw8Uhia6AribBBynqnr9qxVOTkaUMnAnzqvXYjEDctsoXxzeQ2ibqWt0/0");
        LattePreference.addCustomAppProfile("gender", "黑");
        LattePreference.addCustomAppProfile("address", "辽宁");
        //已经注册并登录成功
        AccountManager.setSignState(true);
        signListener.onSignInSuccess();

//        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
//        final UserProfile profile = new UserProfile(1, "123", "http://wx.qlogo.cn/mmopen/guWqj0vybsIHxY2BIqqI3iaSHcbWZXiaSQysU0JKwmqjqMw8Uhia6AribBBynqnr9qxVOTkaUMnAnzqvXYjEDctsoXxzeQ2ibqWt0/0", "黑", "辽宁");
//        DataBaseManager.getInstance().getDao().insert(profile);
    }

    public static void onSignUp(String request, ISignListener signListener) {
//        final JSONObject profileJson = JSON.parseObject(request).getJSONObject("data");
//        final long userId = profileJson.getLong("usetId");
//        final String name = profileJson.getString("name");
//        final String avatar = profileJson.getString("avatar");
//        final String gender = profileJson.getString("gender");
//        final String address = profileJson.getString("address");

        LattePreference.addCustomAppProfile("usetId", "1");
        LattePreference.addCustomAppProfile("name", "123");
        LattePreference.addCustomAppProfile("avatar", "http://wx.qlogo.cn/mmopen/guWqj0vybsIHxY2BIqqI3iaSHcbWZXiaSQysU0JKwmqjqMw8Uhia6AribBBynqnr9qxVOTkaUMnAnzqvXYjEDctsoXxzeQ2ibqWt0/0");
        LattePreference.addCustomAppProfile("gender", "黑");
        LattePreference.addCustomAppProfile("address", "辽宁");

        //已经注册并登录成功
        AccountManager.setSignState(true);
        signListener.onSignUpSuccess();

//        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
//        final UserProfile profile = new UserProfile(1, "123", "http://wx.qlogo.cn/mmopen/guWqj0vybsIHxY2BIqqI3iaSHcbWZXiaSQysU0JKwmqjqMw8Uhia6AribBBynqnr9qxVOTkaUMnAnzqvXYjEDctsoXxzeQ2ibqWt0/0", "黑", "辽宁");
//        DataBaseManager.getInstance().getDao().insert(profile);
    }
}
