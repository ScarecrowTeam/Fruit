package com.fruit.back.controller;

import com.fruit.back.config.interceptor.AuthCheck;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试类
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/test")
public class TestController {

    /**
     * 测试接口
     * @return
     */
    @GetMapping(value = "/output")
    public static String output() {
        return "hello world!";
    }

    /**
     * 测试接口
     * @return
     */
    @AuthCheck
    @GetMapping(value = "/login")
    public static String login() {
        return "login success!";
    }
}
