/*
 * Zenlayer.com Inc.
 * Copyright (c) 2014-2020 All Rights Reserved.
 */
package com.fantastic.platform.dev.integration;

import com.fantastic.platform.dev.util.model.BizException;
import com.fantastic.platform.dev.util.model.ResultInfo;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author barry.sun
 * @Description
 * @Date 2020/4/2 4:06 下午
 * @Version V1.0
 */
public class ResultUtils {

    /**
     * 构建返回
     *
     * @param resultInfo
     * @return
     */
    public static <T> Optional<T> buildReturn(ResultInfo<T> resultInfo) {
        return buildReturn(resultInfo, false);
    }

    public static <T> List<T> buildListReturn(ResultInfo<List<T>> resultInfo) {
        return buildListReturn(resultInfo, false);
    }

    public static <T> List<T> buildListReturn(ResultInfo<List<T>> resultInfo, boolean failedNotThrowException) {
        if (resultInfo.isSuccess()) {
            return resultInfo.getData();
        } else {
            if (failedNotThrowException) {
                return Collections.emptyList();
            } else {
                throw new BizException(resultInfo.getCode(), resultInfo.getMsg(), resultInfo.getRet());
            }
        }
    }

    public static <T> Optional<T> buildReturn(ResultInfo<T> resultInfo, boolean failedNotThrowException) {
        if (resultInfo.isSuccess()) {
            return Optional.ofNullable(resultInfo.getData());
        } else {
            if (failedNotThrowException) {
                return Optional.empty();
            } else {
                throw new BizException(resultInfo.getCode(), resultInfo.getMsg(), resultInfo.getRet());
            }
        }
    }

    public static Boolean buildBooleanReturn(ResultInfo resultInfo) {
        if (resultInfo.isSuccess()) {
            return true;
        } else {
            throw new BizException(resultInfo.getCode(), resultInfo.getMsg(), resultInfo.getRet());
        }
    }

    public static Boolean buildDataBooleanReturn(ResultInfo<Boolean> resultInfo) {
        if (resultInfo.isSuccess()) {
            return resultInfo.getData();
        } else {
            throw new BizException(resultInfo.getCode(), resultInfo.getMsg(), resultInfo.getRet());
        }
    }
}
