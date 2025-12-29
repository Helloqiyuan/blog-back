package com.qiyuan.handler;

import com.qiyuan.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.Arrays;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(DuplicateKeyException.class)
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        String err = e.getMessage();
        String[] s = err.split(" ");
        log.warn("unique键冲突:{}", e.getMessage());
        return Result.error(s[9] + "已存在");
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.warn("全局异常:{}", e.getMessage());
        System.out.println(Arrays.toString(e.getStackTrace()));
        return Result.error(e.getMessage());
    }
}
