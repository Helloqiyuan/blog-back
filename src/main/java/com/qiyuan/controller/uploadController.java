package com.qiyuan.controller;

import com.qiyuan.constant.UploadConstant;
import com.qiyuan.vo.Result;
import com.qiyuan.utils.AliyunOSSUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
@Slf4j
@Tag(name = "上传接口")
public class uploadController {
    @Autowired
    private AliyunOSSUtil aliyunOSSUtil;

    /**
     * 通用图片上传接口
     */
    @PostMapping
    @Operation(summary = "通用图片上传接口")
    public Result upload(MultipartFile file) {
        log.info("上传文件开始:{}", file.getOriginalFilename());
        String url;
        try {
            url = aliyunOSSUtil.upload(file);
        } catch (Exception e) {
            return Result.error(UploadConstant.UPLOAD_FAIL);
        }
        return Result.success(url);
    }

}
