package com.zhm.gen.common.web.domain.response;

/**
 * Describe: 统 一 返 回 状 态 码
 * Author: zhm
 * CreateTime: 2019/10/23
 * */
public enum ResultCode {

    /**
     * 成功
     */
    SUCCESS(200,"操作成功"),
    /**
     * 失败
     */
    FAILURE(500,"操作失败");

    /**
     * 状 态 码
     * */
    private final int code;

    /**
     * 携 带 消 息
     * */
    private final String message;

    /**
     * 构 造 方 法
     * */
    ResultCode(int code, String message) {

        this.code = code;

        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
