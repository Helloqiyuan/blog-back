package com.qiyuan.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class NotePageQueryDTO extends PageQueryDTO{
    private String content;
    /**
     * -1 全部类型
     */
    private Integer typeId;
}
