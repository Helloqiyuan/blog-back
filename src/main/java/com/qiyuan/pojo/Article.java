package com.qiyuan.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 文章
 * */
@Data
public class Article implements Serializable {
    /**
     * 状态为草稿
     * */
    public static final Integer DRAFT = 0;
    /**
     * 状态为发布
     * */
    public static final Integer PUBLISH = 1;
    /**
     * 状态为隐藏
     * */
    public static final Integer HIDED = 2;

    private Integer id;
    private String title;
    private String subTitle;
    private String content;
    private String cover;
    private Integer authorId;
    private Integer status;
    private Integer viewCount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
}
