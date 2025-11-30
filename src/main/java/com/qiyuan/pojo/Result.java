package com.qiyuan.pojo;

import lombok.Data;
/**
 * 结果
 * */
@Data
public class Result {
    private Integer code;
    private Object data;
    private String message;
    public static Result success(){
        Result result = new Result();
        result.setCode(1);
        result.setMessage("success");
        return result;
    }
    public static Result success(Object data){
        Result result = new Result();
        result.setCode(1);
        result.setData(data);
        result.setMessage("success");
        return result;
    }
    public static Result error(String message){
        Result result = new Result();
        result.setCode(0);
        result.setMessage(message);
        return result;
    }
}
