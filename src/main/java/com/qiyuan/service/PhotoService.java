package com.qiyuan.service;

import com.qiyuan.pojo.Photo;
import com.qiyuan.pojo.PhotoType;

import java.util.List;

public interface PhotoService {
    Photo getPhotoRandom();

    void insertPhoto(Photo photo);
    
    void deletePhoto(Integer id);
    
    Photo getPhotoById(Integer id);
    
    void updatePhoto(Photo photo);

    List<Photo> getAllPhotos();

    List<PhotoType> getAllTypes();
}