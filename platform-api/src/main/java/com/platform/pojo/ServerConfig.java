package com.platform.pojo;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @program: platform
 * @description: 程序自启组件类
 * @author: Yuan
 * @create: 2020-09-16 15:06
 **/
@Component
public class ServerConfig {

    @PostConstruct
    public void automatic(){

    }
}
