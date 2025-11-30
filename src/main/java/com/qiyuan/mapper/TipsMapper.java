package com.qiyuan.mapper;

import com.qiyuan.pojo.Tips;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TipsMapper {
    void insertTips(Tips tips);
    Integer deleteTipsById(Integer id);
    Tips getTipsById(Integer id);
    Integer updateTips(Tips tips);

    List<Tips> getAllTips();
}
