package com.qiyuan.dto;

import lombok.Data;

/**
 * 分页查询参数
 * */
@Data
public class pageQueryDTO {
    private Integer page;
    private Integer pageSize;
}
