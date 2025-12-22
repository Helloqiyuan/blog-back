package com.qiyuan.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页查询参数
 * */
@Data
public class PageQueryDTO implements Serializable {
    private Integer page;
    private Integer pageSize;
}
