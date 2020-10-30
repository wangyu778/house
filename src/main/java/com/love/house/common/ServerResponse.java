package com.love.house.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: wy
 * @Date: 2020/10/26 15:35
 * @JsonSerialize 确保序列化JSON时，如果是null对象，其key也会消失。
 * @NoArgsConstructor //生成无参构造，确保在RPC调用时，不会出现反序列失败
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@NoArgsConstructor
public class ServerResponse<T> implements Serializable {
    private int status;
    private String msg;
    private T data;

    private ServerResponse(int status) {
        this.status = status;
    }

    private ServerResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private ServerResponse(int status, T data) {
        this.status = status;
        this.data = data;
    }

    private ServerResponse(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功
     * @JsonIgnore 使之不在JSON序列化结果当中
     * @return code200
     */
    public boolean isSuccess() {
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    /**
     * 失败
     * @JsonIgnore 可以快速进行成功与否的条件判断，判断false时，不用加!
     * @return code400
     */
    @JsonIgnore
    public boolean isFail() {
        return this.status != ResponseCode.SUCCESS.getCode();
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    /**
     * 快速构建返回结果 成功时的调用
     * @param <T> 泛型
     * @return 成功code
     */
    public static <T> ServerResponse<T> createBySuccess() {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> ServerResponse<T> createBySuccessMessage(String msg) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), msg);
    }

    public static <T> ServerResponse<T> createBySuccess(T data) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), data);
    }

    public static <T> ServerResponse<T> createBySuccess(String msg, T data) {
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(), msg, data);
    }

    /**
     * 失败的时候调用
     * @param <T> 泛型
     * @return 失败code
     */
    public static <T> ServerResponse<T> createByError() {
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getMsg());
    }

    public static <T> ServerResponse<T> createByErrorMessage(String errorMessage) {
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(), errorMessage);
    }

    public static <T> ServerResponse<T> createByErrorCodeMessage(int errorCode, String errorMessage) {
        return new ServerResponse<T>(errorCode, errorMessage);
    }
}
