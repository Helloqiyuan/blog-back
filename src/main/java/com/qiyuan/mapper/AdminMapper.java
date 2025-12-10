package com.qiyuan.mapper;

import com.qiyuan.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

@Mapper
public interface AdminMapper {
    Admin getAdminById(Integer id);

    void insertAdmin(Admin admin);

    Integer deleteAdminById(Integer id);

    Admin getAdminByCreateTime(LocalDateTime createTime);

    Integer updateAdmin(Admin admin);

    Admin getAdminByAccount(String account);
}
