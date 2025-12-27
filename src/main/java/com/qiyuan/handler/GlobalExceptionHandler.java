package com.qiyuan.handler;

import com.qiyuan.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e){
        log.warn("全局异常:{}",e.getMessage());
        System.out.println(Arrays.toString(e.getStackTrace()));
        return Result.error(e.getMessage());
    }
}
