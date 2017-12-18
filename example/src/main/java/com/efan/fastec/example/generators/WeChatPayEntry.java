package com.efan.fastec.example.generators;

import com.efan.latte.wechat.templates.WXPayEntryTemplate;
import com.example.annotations.PayEntryGenerator;


/**
 * Created by Administrator on 2017/12/12.
 */
@PayEntryGenerator(
        packageName = "com.efan.fastec.example",
        payEntryTemplate = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
