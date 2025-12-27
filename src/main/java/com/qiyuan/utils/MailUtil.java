package com.qiyuan.utils;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailUtil {
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String from;
    @Value("${spring.mail.from-name}")
    private String fromName;


    /**
     * 发送邮件
     * @param to      收件人
     * @param subject 标题
     * @param text    内容
     */
    public void sendEmail(String to, String subject, String text) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(message, false, "UTF-8");

            // 这里才是关键
            helper.setFrom(
                    new InternetAddress(from, fromName)
            );

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, false); // false = 纯文本

            javaMailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("发送邮件失败", e);
        }
    }

}
