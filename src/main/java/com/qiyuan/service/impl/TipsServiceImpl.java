package com.qiyuan.service.impl;

import com.qiyuan.mapper.TipsMapper;
import com.qiyuan.pojo.Tips;
import com.qiyuan.service.TipsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TipsServiceImpl implements TipsService {
    @Autowired
    private TipsMapper tipsMapper;

    @Override
    public void insertTips(Tips tips) {
        tips.setStatus(Tips.ENABLE);
        tips.setUpdateTime(LocalDateTime.now());
        tips.setCreateTime(LocalDateTime.now());
        tipsMapper.insertTips(tips);
    }

    @Override
    public void deleteTips(Integer id) {
        tipsMapper.deleteTipsById(id);
    }

    @Override
    public Tips getTipsById(Integer id) {
        return tipsMapper.getTipsById(id);
    }

    @Override
    public void updateTips(Tips tips) {
        tips.setUpdateTime(LocalDateTime.now());
        tipsMapper.updateTips(tips);
    }
}
