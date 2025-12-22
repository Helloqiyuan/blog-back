package com.qiyuan.service;

import com.qiyuan.dto.LogPageQueryDTO;
import com.qiyuan.pojo.Log;
import com.qiyuan.vo.PageResult;

public interface LogService {
    PageResult<Log> page(LogPageQueryDTO logPageQueryDTO);
}
