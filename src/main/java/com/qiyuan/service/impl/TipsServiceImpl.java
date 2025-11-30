package com.qiyuan.service.impl;

import com.qiyuan.constant.TipsConstant;
import com.qiyuan.exception.TipsException;
import com.qiyuan.mapper.TipsMapper;
import com.qiyuan.pojo.Tips;
import com.qiyuan.service.TipsService;
import com.qiyuan.utils.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Override
    public List<Tips> getRandomTips(Integer count) {
        List<Tips> res = new ArrayList<>();
        List<Tips> allTips = getAllTips();
        Set<Integer> ids = new HashSet<>();
        while(ids.size() < count){
            ids.add(MathUtil.getRandomInt(0,allTips.size() - 1));
        }
        ids.forEach(id -> res.add(allTips.get(id)));
        return res;
    }

    private List< Tips> getAllTips(){
        return tipsMapper.getAllTips();
    }
}
