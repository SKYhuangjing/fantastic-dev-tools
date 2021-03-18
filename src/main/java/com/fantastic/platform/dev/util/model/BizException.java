/*
 * Zenlayer.com Inc.
 * Copyright (c) 2014-2021 All Rights Reserved.
 */
package com.fantastic.platform.dev.util.model;

import cn.hutool.core.util.StrUtil;

import java.io.Serializable;

public class BizException extends RuntimeException implements Serializable {
    private static final long     serialVersionUID = -9159942779510206342L;
    private              String   exceptionCode;
    private              Integer  exceptionRet;
    private              Object[] formatParams;

    public BizException(String exceptionCode, String exceptionMsg, Integer exceptionRet) {
        super(exceptionMsg);
        this.formatParams = null;
        this.exceptionCode = exceptionCode;
        this.exceptionRet = exceptionRet;
    }

    public BizException(BizExceptionEnumInterface e) {
        this(e.getCode(), e.getMsg(), e.getRet());
    }

    public BizException(BizExceptionEnumInterface e, String exceptionMsg) {
        this(e.getCode(), exceptionMsg, e.getRet());
    }

    public String getExceptionCode() {
        return this.exceptionCode;
    }

    public Integer getExceptionRet() {
        return this.exceptionRet;
    }

    public BizException(BizExceptionEnumInterface bizExceptionCodeEnum, Object... formatParams) {
        this(bizExceptionCodeEnum);
        this.formatParams = formatParams;
    }

    public BizException(BizExceptionEnumInterface bizExceptionCodeEnum, String messagePattern, Object... formatParams) {
        this(bizExceptionCodeEnum, getFormatMsg(messagePattern, formatParams));
        this.formatParams = formatParams;
    }

    public BizException(BizExceptionEnumInterface bizExceptionCodeEnum, Throwable cause) {
        this(bizExceptionCodeEnum);
        super.initCause(cause);
    }

    private static String getFormatMsg(String messagePattern, Object... formatParams) {
        return StrUtil.format(messagePattern, formatParams);
    }
}
