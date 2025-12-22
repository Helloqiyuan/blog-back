package com.qiyuan.controller;

import com.qiyuan.dto.LogPageQueryDTO;
import com.qiyuan.pojo.Log;
import com.qiyuan.service.LogService;
import com.qiyuan.vo.PageResult;
import com.qiyuan.vo.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
@Slf4j
@Tag(name = "日志接口")
public class LogController {
    @Autowired
    private LogService logService;
    @GetMapping("/page")
    @Operation(summary = "分页查询日志")
    public Result page(LogPageQueryDTO logPageQueryDTO){
        log.info("分页查询日志:{}", logPageQueryDTO);
        PageResult<Log> logs = logService.page(logPageQueryDTO);
        return Result.success(logs);
    }
}
