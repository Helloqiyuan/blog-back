package com.qiyuan.mapper;

import com.qiyuan.pojo.PhotoType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PhotoTypeMapper {
    List<PhotoType> getAllTypes();
}
