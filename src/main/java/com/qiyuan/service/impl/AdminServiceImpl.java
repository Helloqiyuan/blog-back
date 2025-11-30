package com.qiyuan.service.impl;

import com.qiyuan.mapper.AdminMapper;
import com.qiyuan.pojo.Admin;
import com.qiyuan.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin getAdminById(Integer AdminId) {
        return adminMapper.getAdminById(AdminId);
    }

    @Override
    public void insertAdmin(Admin admin) {
        if (admin.getLastLoginTime() == null) admin.setLastLoginTime(LocalDateTime.now());
        admin.setUpdateTime(LocalDateTime.now());
        admin.setCreateTime(LocalDateTime.now());
        adminMapper.insertAdmin(admin);
    }

    @Override
    public void deleteAdminById(Integer id) {
        adminMapper.deleteAdminById(id);
    }

    @Override
    public Admin getAdminByCreateTime(LocalDateTime createTime) {
        return adminMapper.getAdminByCreateTime(createTime);
    }
}
