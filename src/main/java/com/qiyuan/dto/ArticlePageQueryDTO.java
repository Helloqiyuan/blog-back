package com.qiyuan.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ArticlePageQueryDTO extends pageQueryDTO implements Serializable {
    /**
     * 分页查询时携带的查询内容
     * */
    private String searchContent;

}
