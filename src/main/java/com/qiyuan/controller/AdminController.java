package com.qiyuan.controller;

import com.qiyuan.annotation.Test;
import com.qiyuan.pojo.Admin;
import com.qiyuan.pojo.Result;
import com.qiyuan.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@RestController()
@RequestMapping("/admin")
@Slf4j
public class AdminController {
    @Autowired
    private AdminService adminService;
    /**
     * 根据id查询管理员
     */
    @GetMapping
    public Result getAdminById(@RequestParam Integer id){
        log.info("查询管理员:{}",id);
        Admin admin = adminService.getAdminById(id);
        return Result.success(admin);
    }
    /**
     * 新增管理员
     * */
    @PostMapping
    public Result insertAdmin(@RequestBody Admin admin){
        log.info("新增管理员:{}",admin);
        adminService.insertAdmin(admin);
        return Result.success();
    }
    /**
     * 根据id删除管理员
     * */
    @DeleteMapping
    public Result deleteAdminById(@RequestParam Integer id){
        log.info("删除管理员:{}",id);
        adminService.deleteAdminById(id);
        return Result.success();
    }
    /**
     * 根据创建时间查询管理员
     * */
    @GetMapping("/time")
    @Test()
    public Result getAdminByCreateTime(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime createTime){
        log.info("查询管理员创建时间:{}",createTime);
        Admin admin = adminService.getAdminByCreateTime(createTime);
        return Result.success(admin);
    }
    /**
     * 更新管理员
     * */
    @PutMapping
    public Result updateAdmin(@RequestBody Admin admin){
        log.info("更新管理员:{}",admin);
        adminService.updateAdmin(admin);
        return Result.success();
    }
}
