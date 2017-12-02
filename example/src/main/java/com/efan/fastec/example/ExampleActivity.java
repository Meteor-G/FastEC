package com.efan.fastec.example;

import com.efan.latte.activities.ProxyActivity;
import com.efan.latte.delegates.LatteDelegate;

public class ExampleActivity extends ProxyActivity {


    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
