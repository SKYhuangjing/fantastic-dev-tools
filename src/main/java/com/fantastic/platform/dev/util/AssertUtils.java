/*
 * Zenlayer.com Inc.
 * Copyright (c) 2014-2021 All Rights Reserved.
 */
package com.fantastic.platform.dev.util;

import com.fantastic.platform.dev.util.model.BizException;
import com.fantastic.platform.dev.util.model.BizExceptionEnumInterface;
import com.fantastic.platform.dev.util.model.ResultInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class AssertUtils {
    private static Logger logger = LoggerFactory.getLogger(AssertUtils.class);

    public static void assertNull(Object obj, BizExceptionEnumInterface e, String messagePattern, Object... argArray) {
        assertTrue(obj == null, e, messagePattern, argArray);
    }

    public static void assertNotNull(Object obj, BizExceptionEnumInterface e, String messagePattern, Object... argArray) {
        assertTrue(obj != null, e, messagePattern, argArray);
    }

    public static void assertNotNull(Object obj, BizExceptionEnumInterface e) {
        assertTrue(obj != null, e);
    }

    public static void assertNotNull(Object obj, BizExceptionEnumInterface e, Object... argArray) {
        assertTrue(obj != null, e, argArray);
    }

    public static void assertNotNull(Object obj, BizExceptionEnumInterface e, String msg) {
        assertTrue(obj != null, e, msg);
    }

    public static void assertNull(Object object, BizExceptionEnumInterface e, String msg) {
        assertTrue(object == null, e, msg);
    }

    public static void assertNull(Object object, BizExceptionEnumInterface e) {
        assertTrue(object == null, e);
    }

    public static void assertEquals(Object o1, Object o2, BizExceptionEnumInterface e) {
        assertEquals(o1, o2, e, e.getMsg());
    }

    public static void assertEquals(Object o1, Object o2, BizExceptionEnumInterface e, String messagePattern, Object... argArray) {
        if (!Objects.equals(o1, o2)) {
            throw new BizException(e, messagePattern, argArray);
        }
    }

    public static void assertEquals(Object o1, Object o2, BizExceptionEnumInterface e, Object... argArray) {
        if (!Objects.equals(o1, o2)) {
            throw new BizException(e, argArray);
        }
    }

    public static void assertTrue(boolean b, BizExceptionEnumInterface e) {
        if (!b) {
            throw new BizException(e);
        }
    }

    public static void assertTrue(boolean b, BizExceptionEnumInterface e, String msg) {
        if (!b) {
            throw new BizException(e, msg);
        }
    }

    public static void assertTrue(boolean b, BizExceptionEnumInterface e, String messagePattern, Object... argArray) {
        if (!b) {
            throw new BizException(e, messagePattern, argArray);
        }
    }

    public static void assertTrue(boolean b, BizExceptionEnumInterface e, Object... argArray) {
        if (!b) {
            throw new BizException(e, argArray);
        }
    }

    public static void assertFalse(boolean b, BizExceptionEnumInterface e) {
        assertTrue(!b, e);
    }

    public static void assertFalse(boolean b, BizExceptionEnumInterface e, String messagePattern, Object... argArray) {
        assertTrue(!b, e, messagePattern, argArray);
    }

    public static void assertSuccess(@SuppressWarnings("rawtypes") ResultInfo result) {
        try {
            assertTrue(result.isSuccess(), new BizExceptionEnumInterface() {
                @Override
                public String getCode() {
                    return result.getCode();
                }

                @Override
                public String getMsg() {
                    return result.getMsg();
                }

                @Override
                public int getRet() {
                    return result.getRet();
                }
            });
        } catch (Exception e) {
            logger.warn("assert fail on result, resultInfo: {}", result);
            throw e;
        }
    }

    public static void assertSuccess(@SuppressWarnings("rawtypes") ResultInfo result, BizException ex) {
        try {
            assertTrue(result.isSuccess(), new BizExceptionEnumInterface() {
                @Override
                public String getCode() {
                    return result.getCode();
                }

                @Override
                public String getMsg() {
                    return result.getMsg();
                }

                @Override
                public int getRet() {
                    return result.getRet();
                }
            });
        } catch (Exception e) {
            logger.warn("assert fail on result, resultInfo: {}", result);
            throw ex;
        }
    }

    public static void assertSuccess(@SuppressWarnings("rawtypes") ResultInfo result, String messagePattern, Object... argArray) {
        try {
            assertTrue(result.isSuccess(), new BizExceptionEnumInterface() {
                @Override
                public String getCode() {
                    return result.getCode();
                }

                @Override
                public String getMsg() {
                    return result.getMsg();
                }

                @Override
                public int getRet() {
                    return result.getRet();
                }
            });
        } catch (Exception e) {
            logger.warn(messagePattern, argArray);
            logger.warn("result :{}", result);
            throw e;
        }
    }

}