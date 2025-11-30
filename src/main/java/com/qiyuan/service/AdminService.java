package com.qiyuan.service;

import com.qiyuan.pojo.Admin;

import java.time.LocalDateTime;


public interface AdminService {
    Admin getAdminById(Integer AdminId);

    void insertAdmin(Admin admin);

    void deleteAdminById(Integer id);

    Admin getAdminByCreateTime(LocalDateTime createTime);
}
