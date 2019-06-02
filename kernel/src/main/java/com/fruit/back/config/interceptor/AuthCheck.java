package com.fruit.back.config.interceptor;

import java.lang.annotation.*;

/**
 * TOKEN验证注解：作用于方法上
 *
 * create by wzy
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthCheck {
}
