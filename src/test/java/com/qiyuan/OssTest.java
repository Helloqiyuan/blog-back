package com.qiyuan;

import com.qiyuan.utils.AliyunOSSUtil;
import com.qiyuan.prop.AliyunSecretProperty;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = BlogBackApplication.class)
class OssTest {
    @Autowired
    private AliyunSecretProperty aliyunSecretProperty;
    @Autowired
    private AliyunOSSUtil aliyunOSSUtil;
    @Test
     void test(){
        System.out.println(aliyunSecretProperty.getOssAccessKeyId());
        System.out.println(aliyunSecretProperty.getOssAccessKeySecret());
    }
}
