package com.qiyuan.controller;

import com.qiyuan.pojo.Result;
import com.qiyuan.utils.AliyunOSSUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
@Slf4j
public class uploadController {
    @Autowired
    private AliyunOSSUtil aliyunOSSUtil;

    @PostMapping
    public Result upload(MultipartFile file) {
        log.info("上传文件开始:{}", file.getOriginalFilename());
        String url = aliyunOSSUtil.upload(file);
        return Result.success(url);
    }
}
