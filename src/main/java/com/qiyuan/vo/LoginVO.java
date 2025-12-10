package com.qiyuan.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginVO {
    private String nickname;
    private String token;
}
