package com.qiyuan.utils;

import com.aliyun.oss.*;
import com.aliyun.oss.common.auth.*;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyun.oss.model.PutObjectRequest;
import com.qiyuan.prop.AliyunSecretProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * OSS SDK 快速接入示例
 * 演示如何初始化 OSS 客户端并列出所有 Bucket
 */
@Component
public class AliyunOSSUtil {
    @Autowired
    private AliyunSecretProperty aliyunSecretProperty;

    public String upload(MultipartFile file) {
        // 填写您的AccessKey信息。
        String accessKeyId = aliyunSecretProperty.getOssAccessKeyId();
        // 填写您的AccessKey Secret。
        String accessKeySecret = aliyunSecretProperty.getOssAccessKeySecret();
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String endpoint = aliyunSecretProperty.getEndpoint(); // http://oss-cn-shenzhen.aliyuncs.com
        // 填写Bucket名称，例如examplebucket。
        String bucketName = aliyunSecretProperty.getBucketName(); // blog-qiyuan
        // 填写Bucket所在地域。以华东1（杭州）为例，Region填写为cn-hangzhou。
        String region = aliyunSecretProperty.getRegion(); // cn-shenzhen

        // 创建OSSClient实例。
        // 当OSSClient实例不再使用时，调用shutdown方法以释放资源。
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
        OSS ossClient = OSSClientBuilder.create()
                .endpoint(endpoint)
                .credentialsProvider(new DefaultCredentialProvider(accessKeyId, accessKeySecret))
                .clientConfiguration(clientBuilderConfiguration)
                .region(region)
                .build();
        // 文件路径
        StringBuilder fileURL = new StringBuilder();
        try {
            // bg.png -->png
            if (file == null || file.getOriginalFilename() == null) {
                throw new IOException("file is null");
            }
            // 文件类型        ps: getOriginalFilename()输出文件名-->abc.txt
            String objectType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
            // 当前年份
            String currentYear = String.valueOf(LocalDateTime.now().getYear());
            // 当前月份
            String currentMonth = String.valueOf(LocalDateTime.now().getMonth().getValue());
            // 当前日
            String currentDay = String.valueOf(LocalDateTime.now().getDayOfMonth());
            // 文件名 2/15/abc.png
            String uuidName = currentYear + "/" + currentMonth + "/" + currentDay + "/" + UUID.randomUUID() + "." + objectType;
            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, uuidName, new ByteArrayInputStream(file.getBytes()));

            // 拼接图片路径
            fileURL
                    .append("https://")
                    .append(bucketName)
                    .append(".")
                    .append(endpoint.substring(7))
                    .append("/")
                    .append(uuidName);
            ossClient.putObject(putObjectRequest);
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } catch (IOException e) {
            System.out.println("IOException" + e.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return fileURL.toString();
    }
}