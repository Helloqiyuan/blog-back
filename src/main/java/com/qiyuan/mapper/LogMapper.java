package com.qiyuan.mapper;

import com.github.pagehelper.Page;
import com.qiyuan.dto.LogPageQueryDTO;
import com.qiyuan.pojo.Log;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogMapper {
    void insertLog(Log log);

    Page<Log> page(LogPageQueryDTO logPageQueryDTO);
}
