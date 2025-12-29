package com.qiyuan;


import org.junit.jupiter.api.Test;

import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class TestCheckNetwork {

    @Test
    public void testCheckNetwork() {
        System.out.println("检查网络接口是否有网线连接");
        for (String name : NetworkStatusChecker()) {
            System.out.println(name + " 已连接");
        }
    }

    private List<String> NetworkStatusChecker() {
        List<String> allConnected = new ArrayList<>();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface ni = networkInterfaces.nextElement();
                if (!ni.isLoopback()) {
                    if (ni.isUp()) {
                        allConnected.add(ni.getInetAddresses().toString());
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("检测失败：" + e.getMessage());
        }
        return allConnected;
    }
}
