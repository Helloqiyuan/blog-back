package com.qiyuan;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public class JSONTest {
    @Test
    public void test(){
        try {
            File file = new File("D:\\Users\\Desktop\\blog\\blog-back\\src\\test\\java\\com\\qiyuan\\html.html");
            JSONObject o = new JSONObject();
            String content = new String(new FileInputStream(file).readAllBytes(), "UTF-8");
            o.put("content",content);
            System.out.println(o);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
