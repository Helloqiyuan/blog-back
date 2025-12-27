package com.qiyuan.service.impl;

import com.qiyuan.constant.PhotoConstant;
import com.qiyuan.exception.PhotoException;
import com.qiyuan.mapper.PhotoMapper;
import com.qiyuan.mapper.PhotoTypeMapper;
import com.qiyuan.pojo.Photo;
import com.qiyuan.pojo.PhotoType;
import com.qiyuan.service.PhotoService;
import com.qiyuan.utils.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {
    @Autowired
    private PhotoMapper photoMapper;
    @Autowired
    private PhotoTypeMapper photoTypeMapper;

    @Override
    public Photo getPhotoRandom() {
        // 数据库的数据是从0开始的
        Integer rand = MathUtil.getRandomInt(0, photoMapper.getPhotoTotalCount() - 1);
        return photoMapper.getPhotoRandom(rand);
    }

    @Override
    public void insertPhoto(Photo photo) {
        photo.setUpdateTime(LocalDateTime.now());
        photo.setCreateTime(LocalDateTime.now());
        photoMapper.insert(photo);
    }

    @Override
    public void deletePhoto(Integer id) {
        Integer i = photoMapper.deleteById(id);
        if (i == 0) {
            throw new PhotoException(PhotoConstant.CAN_NOT_DELETE_NOT_EXIST_PHOTO);
        }
    }

    @Override
    public Photo getPhotoById(Integer id) {
        return photoMapper.selectById(id);
    }

    @Override
    public void updatePhoto(Photo photo) {
        photo.setUpdateTime(LocalDateTime.now());
        Integer i = photoMapper.update(photo);
        if (i == 0) {
            throw new PhotoException(PhotoConstant.CAN_NOT_UPDATE_NOT_EXIST_PHOTO);
        }
    }

    @Override
    public List<Photo> getAllPhotos() {
        return photoMapper.getAllPhotos();
    }

    @Override
    public List<PhotoType> getAllTypes() {
        return photoTypeMapper.getAllTypes();
    }
}