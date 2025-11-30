package com.qiyuan.utils;


public class MathUtil {
    /**
     * 随机生成一个整数
     * */
    public static int getRandomInt(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }
}
