package com.fantastic.platform.dev.util;

import com.fantastic.platform.dev.util.model.BizExceptionEnumInterface;
import com.fantastic.platform.dev.util.model.ResultInfo;

public class ResultUtil {

    public static <T> ResultInfo<T> success(T data) {
        ResultInfo<T> resultInfo = new ResultInfo();
        resultInfo.succeed(data);
        return resultInfo;
    }

    public static <T> ResultInfo<T> success() {
        ResultInfo<T> resultInfo = new ResultInfo();
        resultInfo.succeed();
        return resultInfo;
    }

    public static <T> ResultInfo<T> error(Integer ret, String code, String message) {
        ResultInfo<T> resultInfo = new ResultInfo();
        resultInfo.fail(ret, code, message);
        return resultInfo;
    }

    public static <T> ResultInfo<T> error(BizExceptionEnumInterface message) {
        ResultInfo<T> resultInfo = new ResultInfo();
        resultInfo.fail(message);
        return resultInfo;
    }

    public static <T> ResultInfo<T> error(BizExceptionEnumInterface message, String desc) {
        ResultInfo<T> resultInfo = new ResultInfo();
        resultInfo.fail(message.getRet(), message.getCode(), desc);
        return resultInfo;
    }

}
