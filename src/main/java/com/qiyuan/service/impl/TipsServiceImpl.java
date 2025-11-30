package com.qiyuan.service.impl;

import com.qiyuan.constant.TipsConstant;
import com.qiyuan.exception.TipsException;
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
        Integer i = tipsMapper.deleteTipsById(id);
        if (i == 0) {
            throw new TipsException(TipsConstant.CAN_NOT_DELETE_NOT_EXIST_TIPS);
        }
    }

    @Override
    public Tips getTipsById(Integer id) {
        Tips tips = tipsMapper.getTipsById(id);
        if (tips == null) {
            throw new TipsException(TipsConstant.TIPS_NOT_EXIST);
        }
        return tips;
    }

    @Override
    public void updateTips(Tips tips) {
        tips.setUpdateTime(LocalDateTime.now());
        Integer i = tipsMapper.updateTips(tips);
        if (i == 0) {
            throw new TipsException(TipsConstant.CAN_NOT_UPDATE_NOT_EXIST_TIPS);
        }
    }
}
