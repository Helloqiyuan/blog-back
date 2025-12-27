package com.qiyuan.mapper;

import com.qiyuan.pojo.Photo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PhotoMapper {
    /**
     * 获取数据库中第rand - 1条数据
     * */
    Photo getPhotoRandom(Integer rand);

    Integer getPhotoTotalCount();

    void insert(Photo photo);
    
    Integer deleteById(Integer id);
    
    Photo selectById(Integer id);
    
    Integer update(Photo photo);

    List<Photo> getAllPhotos();
}