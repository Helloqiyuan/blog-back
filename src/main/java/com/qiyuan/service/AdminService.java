package com.qiyuan.service;

import com.qiyuan.pojo.Admin;
import com.qiyuan.vo.LoginVO;

import java.time.LocalDateTime;


public interface AdminService {
    Admin getAdminById(Integer AdminId);

    void insertAdmin(Admin admin);

    void deleteAdminById(Integer id);

    Admin getAdminByCreateTime(LocalDateTime createTime);

    void updateAdmin(Admin admin);

    /**
     * 登录
     * @param admin 登录信息，包含账号和密码
     * @return token
     */
    LoginVO login(Admin admin);
}
