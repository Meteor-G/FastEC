package com.example.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2017/12/12.
 */
//声明注解是类注解   不是方法注解
@Target(ElementType.TYPE)
//处理注解在源码阶段处理
@Retention(RetentionPolicy.SOURCE)
public @interface EntryGenerator {
    String packageName();

    Class<?> entryTemplate();
}

