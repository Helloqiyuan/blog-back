package com.qiyuan.pojo;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CommentNode<T> {
    private T e;
    private List<CommentNode<T>> children;
    public CommentNode(){
        e = null;
        children = new ArrayList<>();
    }
}
