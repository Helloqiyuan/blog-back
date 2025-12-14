package com.qiyuan.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class LoginVO implements Serializable {
    private String nickname;
    private String token;
}
