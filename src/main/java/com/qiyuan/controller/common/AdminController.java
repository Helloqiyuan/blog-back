package com.qiyuan.controller.common;

import com.qiyuan.annotation.Log;
import com.qiyuan.pojo.Admin;
import com.qiyuan.vo.Result;
import com.qiyuan.service.AdminService;
import com.qiyuan.vo.LoginVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;




@RestController("commonAdminController")
@RequestMapping("/common/admin")
@Slf4j
@Tag(name = "管理员相关")
public class AdminController {
    @Autowired
    private AdminService adminService;

    /**
     * 登录
     */
    @PostMapping("/login")
    @Operation(summary = "登录")
    @Log
    public Result login(@RequestBody Admin admin) {
        log.info("登录:{}", admin);
        LoginVO res = adminService.login(admin);
        return Result.success(res);
    }
}
