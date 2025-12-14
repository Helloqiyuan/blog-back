package com.qiyuan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@ServletComponentScan //filter
@EnableCaching //redis缓存注解支持
public class BlogBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogBackApplication.class, args);
    }

}
