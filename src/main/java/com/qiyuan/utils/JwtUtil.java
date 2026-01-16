package com.qiyuan.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

/**
 * JWT 工具类
 * <p>
 * 基于 JJWT (io.jsonwebtoken) 实现 Token 的生成与解析。
 * 使用 HMAC-SHA 系列算法进行签名验证。
 */
public class JwtUtil {

    /**
     * JWT 密钥字符串（长度必须 ≥ 32 字节）
     */
    private static final String SECRET_STRING =
            "helloqiyuanhelloqiyuanhelloqiyuanhelloqiyuanhelloqiyuanhelloqiyuanhelloqiyuanhelloqiyuanhelloqiyuanhelloqiyuanhelloqiyuanhelloqiyuanhelloqiyuan";

    /**
     * 使用 SECRET_STRING 生成的 HMAC 签名密钥
     */
    private static final SecretKey SECRET_KEY =
            Keys.hmacShaKeyFor(SECRET_STRING.getBytes(StandardCharsets.UTF_8));

    /**
     * 生成 JWT Token
     *
     * @param claims 自定义载荷（会直接写入 JWT 的 payload 部分）
     * @return 生成的 JWT 字符串
     */
    public static String createJWT(Map<String, Object> claims) {
        return Jwts.builder()
                .claims(claims)  // 设置自定义负载
                .signWith(SECRET_KEY) // 设置签名密钥（默认 HS256）
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 设置过期时间（一天）
                .compact();
    }

    /**
     * 解析并验证 JWT Token
     *
     * @param token JWT 字符串
     * @return 如果解析成功，返回 Claims（包含 payload 数据）；
     * 如果验证失败、格式非法或已过期，返回 null
     */
    public static Claims parseJWT(String token) throws Exception {
        return Jwts.parser()
                .verifyWith(SECRET_KEY) // 设置签名密钥（用于校验）
                .build()
                .parseSignedClaims(token) // 解析并验证签名
                .getPayload();             // 获取 payload（Claims）
    }
}
