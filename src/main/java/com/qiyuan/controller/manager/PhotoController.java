package com.qiyuan.controller.manager;

import com.qiyuan.pojo.Photo;
import com.qiyuan.pojo.PhotoType;
import com.qiyuan.service.PhotoService;
import com.qiyuan.vo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("managerPhotoController")
@RequestMapping("/manager/photo")
@Slf4j
@Tag(name = "英雄图片接口")
public class PhotoController {
    @Autowired
    private PhotoService photoService;


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
    /**
     * 新增
     * */
    @PostMapping()
    @Operation(summary = "新增英雄图片")
    public Result insert(@RequestBody Photo photo) {
        log.info("新增英雄图片:{}", photo);
        photoService.insertPhoto(photo);
        return Result.success();
    }
    /**
     * 删除
     */
    @DeleteMapping
    @Operation(summary = "删除英雄图片")
    public Result delete(@RequestParam Integer id) {
        log.info("删除英雄图片:{}", id);
        photoService.deletePhoto(id);
        return Result.success();
    }
    /**
     * 根据id查询
     */
    @GetMapping
    @Operation(summary = "根据id查询英雄图片")
    public Result getPhotoById(@RequestParam Integer id) {
        log.info("查询英雄图片:{}", id);
        Photo photo = photoService.getPhotoById(id);
        return Result.success(photo);
    }
    /**
     * 更新
     */
    @PutMapping
    @Operation(summary = "更新英雄图片")
    public Result update(@RequestBody Photo photo) {
        log.info("更新英雄图片:{}", photo);
        photoService.updatePhoto(photo);
        return Result.success();
    }
    /**
     * 获取所有类型
     */
    @GetMapping("/allType")
    @Operation(summary = "获取所有英雄图片类型")
    public Result getAllTypes() {
        log.info("获取所有英雄图片类型");
        List<PhotoType> types = photoService.getAllTypes();
        return Result.success(types);
    }
    /**
     * 根据类型获取图片
     */
    @GetMapping("/type")
    @Operation(summary = "根据类型获取图片")
    public Result getPhotosByType(@RequestParam Integer typeId) {
        log.info("根据类型获取图片:{}", typeId);
        List<Photo> photos = photoService.getAllPhotos();
        photos = photos.stream().filter(photo -> photo.getTypeId().equals(typeId)).toList();
        return Result.success(photos);
    }

}
