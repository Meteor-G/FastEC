package com.efan.fastec.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.efan.ec.launcher.LauncherDelegate;
import com.efan.ec.main.EcBottomDelegate;
import com.efan.ec.sign.ISignListener;
import com.efan.ec.sign.SingInDelegate;
import com.efan.latte.activities.ProxyActivity;
import com.efan.latte.app.Latte;
import com.efan.latte.delegates.LatteDelegate;
import com.efan.latte.ui.launcher.ILauncherListener;
import com.efan.latte.ui.launcher.OnLanucherFinshTag;

public class ExampleActivity extends ProxyActivity implements ISignListener, ILauncherListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        Latte.getConfigurators().withActivity(this);
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLanuncherFinsh(OnLanucherFinshTag tag) {
        switch (tag) {
            case SINGEN:
                Toast.makeText(this, "启动结束，用户登录了", Toast.LENGTH_SHORT).show();
//                startWithPop(new EcBottomDelegate());
                startWithPop(new EcBottomDelegate());
                break;
            case NOT_SIGNED:
                Toast.makeText(this, "启动结束，用户没登录", Toast.LENGTH_SHORT).show();
                startWithPop(new SingInDelegate());
                break;
        }
    }
}
