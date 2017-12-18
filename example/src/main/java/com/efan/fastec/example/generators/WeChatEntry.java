package com.efan.fastec.example.generators;

import com.efan.latte.wechat.templates.WXEntryTemplate;
import com.example.annotations.EntryGenerator;

/**
 * Created by Administrator on 2017/12/12.
 */
@SuppressWarnings("unused")
@EntryGenerator(
        packageName = "com.efan.fastec.example",
        entryTemplate = WXEntryTemplate.class
)
public interface WeChatEntry {
}

