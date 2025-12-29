package com.qiyuan.mapper;

import com.qiyuan.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    Integer insert(User user);
    User getById(Integer id);

    User getByEmail(String email);
    void update(User user);
}
