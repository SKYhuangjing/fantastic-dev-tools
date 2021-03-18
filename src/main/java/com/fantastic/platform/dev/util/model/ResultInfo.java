/*
 * Zenlayer.com Inc.
 * Copyright (c) 2014-2021 All Rights Reserved.
 */
package com.fantastic.platform.dev.util.model;

import java.io.Serializable;

public class ResultInfo<T> implements Serializable {
    private static final long    serialVersionUID = 4886206811288786668L;
    private              boolean success          = false;
    private              int     ret;
    private              String  code;
    private              String  msg;
    private              T       data;

    public ResultInfo() {
        this.ret = BaseBizExceptionEnum.UNEXPECTED_ERROR.getRet();
        this.code = BaseBizExceptionEnum.UNEXPECTED_ERROR.getCode();
        this.msg = BaseBizExceptionEnum.UNEXPECTED_ERROR.getMsg();
        this.data = null;
    }

    public void copy(ResultInfo<T> other) {
        this.withSuccess(other.isSuccess()).withRet(other.getRet()).withCode(other.getCode()).withMsg(other.getMsg()).withData(
                other.getData());
    }

    public ResultInfo<T> succeed() {
        return this.succeed(null);
    }

    public ResultInfo<T> succeed(T data) {
        return this.succeed(data, "SUCCESS", "success");
    }

    public ResultInfo<T> succeed(T data, String code, String msg) {
        this.success = true;
        this.ret = 0;
        this.code = code;
        this.msg = msg;
        this.data = data;
        return this;
    }

    public ResultInfo<T> withSuccess(boolean success) {
        this.success = success;
        if (success) {
            this.ret = 0;
        }

        return this;
    }

    public ResultInfo<T> withRet(int ret) {
        this.ret = ret;
        return this;
    }

    public ResultInfo<T> withCode(String code) {
        this.code = code;
        return this;
    }

    public ResultInfo<T> withMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public ResultInfo<T> withData(T data) {
        this.data = data;
        return this;
    }

    public ResultInfo<T> fail(BizExceptionEnumInterface e) {
        this.success = false;
        this.ret = e.getRet();
        this.code = e.getCode();
        this.msg = e.getMsg();
        return this;
    }

    public ResultInfo<T> fail(BizException bizException) {
        this.success = false;
        this.ret = bizException.getExceptionRet();
        this.code = bizException.getExceptionCode();
        this.msg = bizException.getMessage();
        return this;
    }

    public ResultInfo<T> fail(ResultInfo<T> other) {
        this.success = false;
        this.ret = other.ret;
        this.code = other.code;
        this.msg = other.msg;
        this.data = other.getData();
        return this;
    }

    public ResultInfo<T> fail(int ret, String code, String msg) {
        this.success = false;
        this.ret = ret;
        this.code = code;
        this.msg = msg;
        return this;
    }

    public static long getSerialversionuid() {
        return 4886206811288786668L;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public int getRet() {
        return this.ret;
    }

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public T getData() {
        return this.data;
    }

    @Override
    public String toString() {
        return "ResultInfo [success=" + this.success + ", ret=" + this.ret + ", code=" + this.code + ", msg=" + this.msg + ", data="
                + this.data + "]";
    }
}
