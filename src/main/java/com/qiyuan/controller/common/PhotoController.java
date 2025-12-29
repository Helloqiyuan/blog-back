package com.qiyuan.controller.common;

import com.qiyuan.pojo.Photo;
import com.qiyuan.vo.Result;
import com.qiyuan.service.PhotoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("commonPhotoController")
@RequestMapping("/common/photo")
@Slf4j
@Tag(name = "英雄图片接口")
public class PhotoController {
    @Autowired
    private PhotoService photoService;

    /**
     * 随机获取一张英雄图片
     * */
    @GetMapping("/random")
    @Operation(summary = "随机获取一张英雄图片")
    public Result getPhotoRandom() {
        log.info("随机获取一张英雄图片");
        Photo photo = photoService.getPhotoRandom();
        return Result.success(photo);
    }
    /**
     * 获取所有英雄图片
     */
    @GetMapping("/all")
    @Operation(summary = "获取所有英雄图片")
    public Result getAllPhotos() {
        log.info("获取所有英雄图片");
        List<Photo> photos = photoService.getAllPhotos();
        return Result.success(photos);
    }

}
