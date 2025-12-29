package com.qiyuan.service.impl;

import com.qiyuan.constant.AdminConstant;
import com.qiyuan.exception.AdminException;
import com.qiyuan.mapper.AdminMapper;
import com.qiyuan.pojo.Admin;
import com.qiyuan.service.AdminService;
import com.qiyuan.utils.JwtUtil;
import com.qiyuan.utils.ThreadLocalUtil;
import com.qiyuan.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin getAdminById(Integer AdminId) {
        Admin admin = adminMapper.getAdminById(AdminId);
        if (admin == null) {
            throw new AdminException(AdminConstant.ADMIN_NOT_EXIST);
        }
        return admin;
    }

    @Override
    public void insertAdmin(Admin admin) {
        admin.setLastLoginTime(LocalDateTime.now());
        admin.setUpdateTime(LocalDateTime.now());
        admin.setCreateTime(LocalDateTime.now());
        adminMapper.insertAdmin(admin);
    }

    @Override
    public void deleteAdminById(Integer id) {
        Integer i = adminMapper.deleteAdminById(id);
        if (i == 0) {
            throw new AdminException(AdminConstant.CAN_NOT_DELETE_NOT_EXIST_ADMIN);
        }
    }

    @Override
    public Admin getAdminByCreateTime(LocalDateTime createTime) {
        Admin admin = adminMapper.getAdminByCreateTime(createTime);
        if (admin == null) {
            throw new AdminException(AdminConstant.ADMIN_NOT_EXIST);
        }
        return admin;
    }

    @Override
    public void updateAdmin(Admin admin) {
        Integer i = adminMapper.updateAdmin(admin);
        if (i == 0) {
            throw new AdminException(AdminConstant.CAN_NOT_UPDATE_NOT_EXIST_ADMIN);
        }
    }

    @Override
    public LoginVO login(Admin admin) {
        Admin adm = adminMapper.getAdminByAccount(admin.getAccount());
        if (adm == null) {
            throw new AdminException(AdminConstant.ADMIN_NOT_EXIST);
        }
        if (!adm.getPassword().equals(admin.getPassword())) {
            throw new AdminException(AdminConstant.PASSWORD_ERROR);
        }
        HashMap<String, Object> claims = new HashMap<>();
//        用于记录登录日志
        ThreadLocalUtil.set(adm.getId());

        claims.put("id", adm.getId());
        claims.put("account", adm.getAccount());
        claims.put("password",adm.getPassword());
        String token = JwtUtil.createJWT(claims);
        return LoginVO
                .builder()
                .id(adm.getId())
                .nickname(adm.getNickname())
                .token(token)
                .exp(JwtUtil.EXP)
                .build();
    }
}
