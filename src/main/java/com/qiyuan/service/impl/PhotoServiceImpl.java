package com.qiyuan.service.impl;

import com.qiyuan.mapper.PhotoMapper;
import com.qiyuan.pojo.Photo;
import com.qiyuan.service.PhotoService;
import com.qiyuan.utils.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoServiceImpl implements PhotoService {
    @Autowired
    private PhotoMapper photoMapper;
    @Override
    public Photo getPhotoRandom() {
        // 数据库的数据是从0开始的
        Integer rand = MathUtil.getRandomInt(0,photoMapper.getPhotoTotalCount() - 1);
        return photoMapper.getPhotoRandom(rand);
    }
}
