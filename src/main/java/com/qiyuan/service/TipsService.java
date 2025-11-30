package com.qiyuan.service;

import com.qiyuan.pojo.Tips;

import java.util.List;

public interface TipsService {
    void insertTips(Tips tips);

    void deleteTips(Integer id);

    Tips getTipsById(Integer id);

    void updateTips(Tips tips);

    List<Tips> getRandomTips(Integer count);
}
