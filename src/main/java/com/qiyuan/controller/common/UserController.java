package com.qiyuan.controller.common;

import com.qiyuan.pojo.User;
import com.qiyuan.service.UserService;
import com.qiyuan.vo.Result;
import com.qiyuan.vo.UserLoginVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("commonUserController")
@RequestMapping("/common/user")
@Slf4j
@Tag(name = "用户接口")
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * 用户注册
     */
    @PostMapping("/register")
    @Operation(summary = "用户注册")
    public Result register(@RequestBody User user) {
        log.info("用户注册:{}", user);
        userService.insert(user);
        return Result.success();
    }
    /**
     * 用户登录
     */
    @PostMapping("/login")
    @Operation(summary = "用户登录")
    public Result login(@RequestBody User user) {
        log.info("用户登录:{}", user);
        UserLoginVO userLoginVO = userService.login(user);
        return Result.success(userLoginVO);
    }
    /**
     * 发送验证码
     */
    @GetMapping("/sendEmailExp")
    @Operation(summary = "发送验证码")
    public Result sendEmailExp(@RequestParam String email) {
        log.info("发送验证码:{}", email);
        userService.sendEmailExp(email);
        return Result.success();
    }
}
