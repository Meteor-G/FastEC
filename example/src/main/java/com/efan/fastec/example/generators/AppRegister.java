package com.efan.fastec.example.generators;

import com.efan.latte.wechat.templates.AppRegisterTemplate;
import com.example.annotations.AppRegisterGenerator;

/**
 * Created by Administrator on 2017/12/12.
 */
@AppRegisterGenerator(
        packageName = "com.efan.fastec.example",
        registerTemplate = AppRegisterTemplate.class
)
public interface AppRegister {
}
