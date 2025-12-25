package com.qiyuan.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FriendLink {
    /**
     * 友链状态：1启用，0禁用
     */
    public static final Integer ENABLE = 1;
    public static final Integer DISABLE = 0;


    private Integer id;
    private String name;
    private String url;
    private String img;
    private String description;
    private String authorEmail;
    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
}
