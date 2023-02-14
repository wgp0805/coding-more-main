package com.codingmore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 前台展示系统接口
 */
@SpringBootApplication
public class CodingmoreWebBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(CodingmoreWebBootstrap.class);
    }
}
