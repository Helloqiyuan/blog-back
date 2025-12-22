package com.qiyuan.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ArticlePageQueryDTO extends PageQueryDTO{
    /**
     * 点击数优先排序
     */
    public static final Integer VIEW_COUNT_DESC = 0;
    /**
     * 发布时间优先排序
     */
    public static final Integer CREATE_TIME_DESC = 1;
    /**
     * 修改时间优先排序
     */
    public static final Integer UPDATE_TIME_DESC = 2;
    private Integer sortType;
    /**
     * 分页查询时携带的查询内容
     * */
    private String searchContent;

}
