/*
 * Zenlayer.com Inc.
 * Copyright (c) 2014-2021 All Rights Reserved.
 */
package com.fantastic.platform.dev.util;

import cn.hutool.core.util.ReflectUtil;

/**
 * @author sky
 */
public class BaseModelHelper {

    public static <E, T> E copy(T source, Class<E> targetClazz) {
        return copy(source, null, targetClazz);
    }

    public static <E, T> E copy(T source, E target, Class<E> targetClazz) {
        if (source != null) {
            if (target == null) {
                target = ReflectUtil.newInstanceIfPossible(targetClazz);
            }

            XBeanUtils.copy(source, target);
        }

        return target;
    }
}