package com.qiyuan.controller;

import com.qiyuan.constant.UploadConstant;
import com.qiyuan.pojo.ArticlePicture;
import com.qiyuan.pojo.EditorResult;
import com.qiyuan.pojo.Result;
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
     * 主页的英雄图上传接口
     */
    @PostMapping
    @Operation(summary = "主页的英雄图上传接口")
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

    /**
     * 文章图片的上传接口
     */
    @PostMapping("/articlePicture")
    @Operation(summary = "文章图片的上传接口")
    public EditorResult uploadArticlePicture(MultipartFile file) {
        log.info("上传文章图片开始:{}", file.getOriginalFilename());
        ArticlePicture res = new ArticlePicture();
        try {
            res.setUrl(aliyunOSSUtil.upload(file));
        } catch (Exception e) {
            return EditorResult.error(UploadConstant.UPLOAD_FAIL);
        }
        log.info("上传文章图片成功:{}", res);

        return EditorResult.success(res);
    }
}
