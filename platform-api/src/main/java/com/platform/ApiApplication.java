package com.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @Description 启动入口
 * @Date 2019/12/24
 * @Created by 67068
 */
@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = "com.platform")
@EnableScheduling
@EnableWebMvc
public class ApiApplication {

    public static void main(String[] args) { SpringApplication.run(ApiApplication.class, args); }
}
