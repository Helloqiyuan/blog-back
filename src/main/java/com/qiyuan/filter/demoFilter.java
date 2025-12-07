package com.qiyuan.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

@WebFilter("/*")
@Slf4j
public class demoFilter implements Filter {
    @Value("${spring.datasource.url}")
    private String datasourceUrl;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = String.valueOf(request.getRequestURL());
        log.info("请求的url为：{}",url);
//        log.info("数据库连接地址为：{}",datasourceUrl);
        filterChain.doFilter(request,response);
    }
}
