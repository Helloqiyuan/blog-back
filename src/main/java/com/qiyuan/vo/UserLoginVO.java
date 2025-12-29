package com.qiyuan.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginVO {
    private Integer id;
    private String username;
    private String token;
    private String exp;
}
