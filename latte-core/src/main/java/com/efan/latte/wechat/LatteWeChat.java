package com.efan.latte.wechat;

import android.app.Activity;

import com.efan.latte.app.ConfigKeys;
import com.efan.latte.app.Latte;
import com.efan.latte.wechat.callbacks.IWeChatSignInCallback;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by Administrator on 2017/12/12.
 */

public class LatteWeChat {
    public static final String APP_ID = Latte.getConfigurations(ConfigKeys.WE_CHAT_APP_ID);
    public static final String APP_SECRET = Latte.getConfigurations(ConfigKeys.WE_CHAT_APP_SECRET);
    private final IWXAPI WXAPI;
    private IWeChatSignInCallback mSignInCallback = null;

    private static final class Holder {
        private static final LatteWeChat INSTENCE = new LatteWeChat();
    }

    public static LatteWeChat getInstence() {
        return Holder.INSTENCE;
    }

    private LatteWeChat() {
        final Activity activity = Latte.getConfigurations(ConfigKeys.ACTIVITY);
        WXAPI = WXAPIFactory.createWXAPI(activity, APP_ID, true);
        WXAPI.registerApp(APP_ID);
    }

    public LatteWeChat onSignSuccess(IWeChatSignInCallback callback) {
        this.mSignInCallback = callback;
        return this;
    }

    public IWeChatSignInCallback getSignInCallback() {
        return mSignInCallback;
    }

    public final IWXAPI getWXAPI() {
        return WXAPI;
    }

    //调用微信的接口
    public final void signIn() {
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        WXAPI.sendReq(req);
    }
}
