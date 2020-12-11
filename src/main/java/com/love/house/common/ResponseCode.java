package com.love.house.common;

/**
 * @Author: wy
 * @Date: 2020/10/26 15:28
 * @Description: 响应状态码
 */
public enum ResponseCode {
    
    // 系统模块
    SUCCESS(200, "操作成功"),
    SAVE_SUCCESS(201,"保存成功"),
    DELETE_SUCCESS(202,"删除成功！"),
    UPDATE_SUCCESS(403,"更新成功！"),

    ERROR(400, "操作失败"),
    SAVE_ERROR(401,"保存失败"),
    DELETE_ERROR(402,"删除失败！"),
    UPDATE_ERROR(403,"更新失败"),

    // 通用模块 1xxxx
    ILLEGAL_ARGUMENT(10000, "参数不合法"),
    REPETITIVE_OPERATION(10001, "请勿重复操作"),
    ACCESS_LIMIT(10002, "请求太频繁, 请稍后再试"),
    MAIL_SEND_SUCCESS(10003, "邮件发送成功"),
    PARAMETER_NOT_EMPTY(10004,"参数不能为空"),
    GRMMAR_RULES_ILLEGAL(10005,"语法规则有误,请检查！");

    ResponseCode(Integer code,String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
