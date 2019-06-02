package com.fruit.back.config;

import com.fruit.back.config.interceptor.InterceptorHandler;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 拦截器应用
 * create by wzy
 */
@SpringBootConfiguration
public class WebConfig extends WebMvcConfigurationSupport {

    /**
     * 配置拦截器
     */
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new InterceptorHandler());
    }
}
