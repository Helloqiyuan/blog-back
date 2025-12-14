package com.qiyuan.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Tips implements Serializable {
    /**
     * 状态为启用
     * */
    public static final Boolean ENABLE = true;
    /**
     * 状态为禁用
     * */
    public static final Boolean DISABLE = false;

    private Integer id;
    private String content;
    private Integer priority;
    private Boolean status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
}
