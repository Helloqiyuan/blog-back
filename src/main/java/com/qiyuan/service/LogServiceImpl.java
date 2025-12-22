package com.qiyuan.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qiyuan.dto.LogPageQueryDTO;
import com.qiyuan.mapper.LogMapper;
import com.qiyuan.pojo.Log;
import com.qiyuan.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogMapper logMapper;

    @Override
    public PageResult<Log> page(LogPageQueryDTO logPageQueryDTO) {
        PageHelper.startPage(logPageQueryDTO.getPage(), logPageQueryDTO.getPageSize());
        Page<Log> p = logMapper.page(logPageQueryDTO);
        return new PageResult<>(p.getTotal(), p.getResult());
    }
}
