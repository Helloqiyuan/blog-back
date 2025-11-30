package com.qiyuan.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Data
@Component
@ConfigurationProperties(prefix = "blog.oss")
public class AliyunSecretProperty {
    private String endpoint;
    private String bucketName;
    private String region;
    private String ossAccessKeyId;
    private String ossAccessKeySecret;
}
