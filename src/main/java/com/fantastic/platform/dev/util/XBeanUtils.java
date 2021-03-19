/*
 * Zenlayer.com Inc.
 * Copyright (c) 2014-2021 All Rights Reserved.
 */
package com.fantastic.platform.dev.util;

import cn.hutool.core.bean.BeanUtil;

/**
 * @author sky
 */
public class XBeanUtils extends BeanUtil {

    public static <T, V> V copy(T source, V target) {
        if (source != null && target != null) {
            copyProperties(source, target);
        }

        return target;
    }
}
