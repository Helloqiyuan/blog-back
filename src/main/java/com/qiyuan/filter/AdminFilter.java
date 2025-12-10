package com.qiyuan.filter;

import com.qiyuan.utils.JwtUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@WebFilter("/*")
@Slf4j
public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = String.valueOf(request.getRequestURL());
        log.info("请求的url为：{}",url);
        log.warn("请求uri{}",request.getRequestURI());
//        log.info("数据库连接地址为：{}",datasourceUrl);
        List<String> requestMethods = List.of("POST","PUT","DELETE");
//        存在需要权限的请求方法时，进行权限校验
        if(requestMethods.contains(request.getMethod()) && !"/admin/login".equals(request.getRequestURI())){
            String token = request.getHeader("Authorization");
            if(token == null){
                response.setStatus(401);
                return;
            }
            token = token.split(" ")[1];
            try{
                JwtUtil.parseJWT(token);
            } catch (Exception e) {
                response.setStatus(401);
                return;
            }
        }
        filterChain.doFilter(request,response);
    }
}
