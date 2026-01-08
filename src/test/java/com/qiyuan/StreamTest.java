package com.qiyuan;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class StreamTest {
    @Test
    public void test(){
        List<Integer> x = new ArrayList<>();
        x.add(1);
        x.add(2);
        x.add(3);
        x.add(4);
        x.add(5);
        List<Integer> list = x.stream().filter(e -> e != 1).toList();
        System.out.println(list);
    }
}
