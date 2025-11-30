package com.qiyuan;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class BlogBackApplicationTests {


    @Test
    void contextLoads() {
        System.out.println(LocalDateTime.now());
    }

}
