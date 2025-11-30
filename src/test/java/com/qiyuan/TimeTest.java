package com.qiyuan;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class TimeTest {
    @Test
    public void test(){
        System.out.println(LocalDateTime.now().getMonth().getValue());
        System.out.println(LocalDateTime.now().getDayOfMonth());
    }
}
