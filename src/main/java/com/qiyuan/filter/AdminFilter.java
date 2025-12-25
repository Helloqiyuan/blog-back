package com.qiyuan.filter;

import com.qiyuan.utils.JwtUtil;
import com.qiyuan.utils.ThreadLocalUtil;
import io.jsonwebtoken.Claims;
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
        String requestURI = request.getRequestURI();
        String requestMethod = request.getMethod();
        log.info("{} {}{}", requestMethod, request.getRequestURL(), request.getQueryString() == null ? "" : "?" + request.getQueryString());

//        存在需要权限的请求方法时，进行权限校验
        if (isNeedLogin(requestURI, requestMethod)) {
            String token = request.getHeader("Authorization");
            if (token == null) {
                response.setStatus(401);
                log.warn("token is null");
                return;
            }
            token = token.split(" ")[1];
            try {
                Claims claims = JwtUtil.parseJWT(token);
                ThreadLocalUtil.set((Integer) claims.get("id"));
                System.out.println("ThreadLocalUtil.get()" + ThreadLocalUtil.get());
            } catch (Exception e) {
                log.warn("token Authorization failed");
                response.setStatus(401);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean isNeedLogin(String uri, String method) {
        List<String> needLoginMethods = List.of("POST", "PUT", "DELETE");
//        新增友链不需要登录
        if ("/friendLink".equals(uri) && "POST".equals(method)) {
            return false;
        }
//        登录不需要登录
        if ("/admin/login".equals(uri)) {
            return false;
        }
        if (needLoginMethods.contains(method)) {
            return true;
        }
        return false;
    }
}
