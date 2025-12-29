package com.qiyuan.service;

import com.qiyuan.pojo.User;
import com.qiyuan.vo.UserLoginVO;

public interface UserService {
    void insert(User user);
    User getById(Integer id);
    UserLoginVO login(User user);

    void sendEmailExp(String email);
}
