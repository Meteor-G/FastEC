package com.efan.latte.wechat.templates;

import com.efan.latte.wechat.BaseWXEntryActivity;
import com.efan.latte.wechat.LatteWeChat;

/**
 * Created by Administrator on 2017/12/12.
 */

public class WXEntryTemplate extends BaseWXEntryActivity {
    @Override
    protected void onResume() {
        super.onResume();
        finish();
        //消除不需要动画效果
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onSignInSuccess(String userInfo) {
        LatteWeChat.getInstence().getSignInCallback().onSignInSuccess(userInfo);
    }
}
