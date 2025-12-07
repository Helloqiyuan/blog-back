package com.qiyuan.pojo;


import lombok.Data;

@Data
public class EditorResult {
    private Integer errno;
    private Object data;
    public static EditorResult success(Object data){
        EditorResult result = new EditorResult();
        result.setErrno(0);
        result.setData(data);
        return result;
    }
    public static EditorResult error(String message){
        EditorResult result = new EditorResult();
        result.setErrno(1);
        result.setData(message);
        return result;
    }
}
