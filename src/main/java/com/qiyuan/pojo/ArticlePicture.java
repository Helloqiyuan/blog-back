package com.qiyuan.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ArticlePicture implements Serializable {
    private String url;
    private String alt;
    private String href;
}
