package com.qiyuan.utils;


public class MathUtil {
    /**
     * 随机生成一个整数
     * @param min 最小值
     * @param max 最大值
     * @return 一个介于[min,max]的整数
     * */
    public static int getRandomInt(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }
    /**
     * 获取一天后的时间戳
     */
    public static long getTimestampOfOneDayLater() {
        return System.currentTimeMillis() + 1000 * 60 * 60 * 24;
    }
}
