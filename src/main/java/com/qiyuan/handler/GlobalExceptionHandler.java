package com.qiyuan.handler;

import com.qiyuan.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e){
        log.warn("全局异常:{}{}",e.getMessage(),e.getStackTrace());
        return Result.error(e.getMessage());
    }
}
