package com.qiyuan.mapper;

import com.qiyuan.pojo.Admin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;

@Mapper
public interface AdminMapper {
    Admin getAdminById(Integer id);

    void insertAdmin(Admin admin);

    void deleteAdminById(Integer id);

    Admin getAdminByCreateTime(LocalDateTime createTime);
}
