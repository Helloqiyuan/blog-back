package com.qiyuan.dto;

import lombok.Data;


@Data
public class ArticlePageQueryDTO extends pageQueryDTO{
    /**
     * 分页查询时携带的查询内容
     * */
    private String searchContent;
}
