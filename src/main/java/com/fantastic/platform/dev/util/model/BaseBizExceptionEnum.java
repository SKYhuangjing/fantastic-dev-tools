/*
 * Zenlayer.com Inc.
 * Copyright (c) 2014-2021 All Rights Reserved.
 */
package com.fantastic.platform.dev.util.model;

public enum BaseBizExceptionEnum implements BizExceptionEnumInterface {
    UNEXPECTED_ERROR(1, "UNEXPECTED_ERROR"),

    // common exception
    /**
     * 500
     */
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

    /**
     * 400
     */
    BAD_REQUEST(400, "Bad Request"),

    /**
     * 401
     */
    UNAUTHORIZED(401, "Unauthorized"),

    /**
     * 403
     */
    FORBIDDEN(403, "Forbidden");

    private String msg;
    private int    ret;

    private BaseBizExceptionEnum(int ret, String msg) {
        this.msg = msg;
        this.ret = ret;
    }

    @Override
    public String getCode() {
        return this.name();
    }

    @Override
    public String getMsg() {
        return this.msg;
    }

    @Override
    public int getRet() {
        return this.ret;
    }
}
