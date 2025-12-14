package com.qiyuan.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 图片
 * */
@Data
public class Photo implements Serializable {
    /**
     * 风景
     * */
    public static final Integer LANDSCAPE = 1;
    /**
     * 肖像
     * */
    public static final Integer PORTRAIT = 2;
    private Integer id;
    private String url;
    private Integer type;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
}
