package com.qiyuan.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class LoginVO implements Serializable {
    private Integer id;
    private String nickname;
    private String token;
    private String exp;
}
