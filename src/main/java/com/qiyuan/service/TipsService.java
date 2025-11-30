package com.qiyuan.service;

import com.qiyuan.pojo.Tips;

public interface TipsService {
    void insertTips(Tips tips);

    void deleteTips(Integer id);

    Tips getTipsById(Integer id);

    void updateTips(Tips tips);
}
