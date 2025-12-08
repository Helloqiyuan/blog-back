package com.qiyuan.controller;

import com.qiyuan.pojo.Photo;
import com.qiyuan.pojo.Result;
import com.qiyuan.service.PhotoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/photo")
@Slf4j
@Tag(name = "图片接口")
public class PhotoController {
    @Autowired
    private PhotoService photoService;

    /**
     * 随机获取一张图片
     * */
    @GetMapping("/random")
    @Operation(summary = "随机获取一张图片")
    public Result getPhotoRandom() {
        log.info("随机获取一张图片");
        Photo photo = photoService.getPhotoRandom();
        log.info("获取图片成功:{}", photo);
        return Result.success(photo);
    }
    /**
     * 批量上传图片
     * */
    @PostMapping("/batch")
    @Operation(summary = "批量上传图片")
    public Result batchUpload(MultipartFile[] files) {
        log.info("批量上传图片:{}", files);
//        TODO
//        photoService.batchUpload(files);
        return Result.success();
    }
}
