package com.qiyuan.mapper;

import com.qiyuan.pojo.Photo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PhotoMapper {
    /**
     * 获取数据库中第rand - 1条数据
     * */
    Photo getPhotoRandom(Integer rand);

    Integer getPhotoTotalCount();
}
