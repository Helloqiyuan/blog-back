package com.qiyuan.service.impl;

import com.qiyuan.constant.UserConstant;
import com.qiyuan.exception.UserException;
import com.qiyuan.mapper.UserMapper;
import com.qiyuan.pojo.User;
import com.qiyuan.service.UserService;
import com.qiyuan.utils.*;
import com.qiyuan.vo.UserLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MailUtil mailUtil;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void insert(User user) {
        user.setLastLoginTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        user.setCreateTime(LocalDateTime.now());
//        获取redis存储的验证码
        String s = redisTemplate.opsForValue().get(user.getEmail());
//        验证码为空
        if(s == null){
            throw new UserException(UserConstant.EXP_NOT_FOUND);
        }
        if(!s.equals(user.getExp())){
            throw new UserException(UserConstant.EXP_MATCH_ERROR);
        }
        Integer i = userMapper.insert(user);
        if (i != 1) {
            throw new UserException(UserConstant.INSERT_ERROR);
        }
    }

    @Override
    public User getById(Integer id) {
        return userMapper.getById(id);
    }

    @Override
    public UserLoginVO login(User user) {
        User u = userMapper.getByEmail(user.getEmail());
        if (u == null) {
            throw new UserException(UserConstant.USER_NOT_EXIST);
        }
        if (!u.getPassword().equals(user.getPassword())) {
            throw new UserException(UserConstant.PASSWORD_ERROR);
        }
        HashMap<String, Object> claims = new HashMap<>();

        u.setLastLoginTime(LocalDateTime.now());
        userMapper.update(u);
        //        用于记录登录日志
        ThreadLocalUtil.set(u.getId());
        claims.put("id", u.getId());
        claims.put("username", u.getUsername());
        claims.put("password", u.getPassword());
        String token = JwtUtil.createJWT(claims);
        return UserLoginVO
                .builder()
                .id(u.getId())
                .username(u.getUsername())
                .token(token)
                .exp(JwtUtil.EXP)
                .build();
    }

    @Override
    public void sendEmailExp(String email) {
        if (email.isEmpty()) {
            throw new UserException(UserConstant.EMAIL_IS_EMPTY);
        }
        String exp = MathUtil.getRandomInt(100000, 999999) + "";
//        存储验证码
        redisTemplate.opsForValue().set(email, exp);
        mailUtil.sendEmail(email, FileUtil.readFile("exp-title.txt"), FileUtil.readExpContentFile(exp));
    }
}
