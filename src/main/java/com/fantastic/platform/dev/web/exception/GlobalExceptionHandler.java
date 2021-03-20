/*
 * Zenlayer.com Inc.
 * Copyright (c) 2014-2021 All Rights Reserved.
 */
package com.fantastic.platform.dev.web.exception;

import com.fantastic.platform.dev.util.model.BaseBizExceptionEnum;
import com.fantastic.platform.dev.util.model.BizException;
import com.fantastic.platform.dev.util.model.ResultInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author sky
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //处理自定义的异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResultInfo exceptionHandler(Exception e) {
        ResultInfo<String> result = new ResultInfo<>();
        result.fail(BaseBizExceptionEnum.INTERNAL_SERVER_ERROR.getRet(), BaseBizExceptionEnum.INTERNAL_SERVER_ERROR.name(),
                BaseBizExceptionEnum.INTERNAL_SERVER_ERROR.getMsg());
        result.withData(e.getMessage());
        logger.warn("error =>", e);
        return result;
    }

    @ExceptionHandler(BizException.class)
    @ResponseBody
    public ResultInfo bizExceptionHandler(BizException e) {
        ResultInfo<String> result = new ResultInfo<>();
        result.fail(e.getExceptionRet(), e.getExceptionCode(), e.getMessage());
        logger.error("biz error =>", e);
        return result;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Object exceptionHandler(MethodArgumentNotValidException e) {
        return exceptionHandler(e, e.getBindingResult());
    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Object exceptionHandler(BindException e) {
        return exceptionHandler(e, e.getBindingResult());
    }

    protected Object exceptionHandler(Exception e, BindingResult bindingResult) {
        ResultInfo<Object> result = new ResultInfo<>();
        result.fail(BaseBizExceptionEnum.BAD_REQUEST.getRet(), BaseBizExceptionEnum.BAD_REQUEST.name(),
                BaseBizExceptionEnum.BAD_REQUEST.getMsg());
        result.withData(bindingResult.getAllErrors());
        logger.warn("bad req error =>", e);
        return result;
    }

}
