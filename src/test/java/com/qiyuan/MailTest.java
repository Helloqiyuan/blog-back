package com.qiyuan;

import com.qiyuan.utils.MailUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MailTest {
    @Autowired
    private MailUtil mailUtil;

    @Test
    public void test(){
        mailUtil.sendEmail("3060998134@qq.com","测试邮件","这是一封测试邮件");
    }
}
