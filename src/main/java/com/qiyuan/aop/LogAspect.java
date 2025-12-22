package com.qiyuan.aop;

import com.qiyuan.mapper.LogMapper;
import com.qiyuan.pojo.Log;
import com.qiyuan.utils.ThreadLocalUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
public class LogAspect {
    @Autowired
    private LogMapper logMapper;

    @Pointcut("execution(* com.qiyuan.controller.*.save(..)) || " +
            "execution(* com.qiyuan.controller.*.update(..)) || " +
            "execution(* com.qiyuan.controller.*.delete(..)) || " +
            "execution(* com.qiyuan.controller.*.upload*(..)) || " +
            "@annotation(com.qiyuan.annotation.Log)")
    public void pt() {
    }

    @Around("pt()")

    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
//        获取类名
        String className = joinPoint.getTarget().getClass().getName();
//        获取方法名
        String methodName = joinPoint.getSignature().getName();
//        获取方法参数
        Object[] args = joinPoint.getArgs();

        long start = System.currentTimeMillis();
//        执行方法
        Object res = joinPoint.proceed(args);

        long end = System.currentTimeMillis();
//        处理MultipartFile参数-> 存储文件名
        String argsFixed = fixMultipartFileParams(args);

        Log log = Log
                .builder()
                .operateAdminId(ThreadLocalUtil.get())
                .className(className)
                .operateTime(LocalDateTime.now())
                .methodName(methodName)
                .methodParams(argsFixed)
                .returnValue(res.toString())
                .costTime(end - start)
                .build();
        logMapper.insertLog(log);
//        返回原方法的结果
        return res;

    }

    /**
     * 处理方法参数是MultipartFile的情况
     * @param args 方法的所有参数
     * @return 字符串化的参数列表
     */
    private String fixMultipartFileParams(Object[] args){
        List<Object> argsFixed = new ArrayList<>();
        for(Object o:args){
            if(o instanceof MultipartFile){
                argsFixed.add(((MultipartFile) o).getOriginalFilename());
            }else if(o instanceof MultipartFile[]){
                List<String> fileNames = new ArrayList<>();
                for(MultipartFile file: (MultipartFile[]) o){
                    fileNames.add(file.getOriginalFilename());
                }
                argsFixed.add(fileNames);
            }else{
                argsFixed.add(o);
            }
        }
        return argsFixed.toString();
    }

}
