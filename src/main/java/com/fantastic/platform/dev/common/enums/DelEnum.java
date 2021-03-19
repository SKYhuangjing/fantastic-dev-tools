/*
 * Zenlayer.com Inc.
 * Copyright (c) 2014-2021 All Rights Reserved.
 */
package com.fantastic.platform.dev.common.enums;

import cn.hutool.core.util.StrUtil;

/**
 * @author jacky
 */
public enum DelEnum {
    /**
     * 删除
     */
    DEL(1),
    /**
     *未删除
     */
    NOT_DEL(0);

    private int value;

    public int getValue() {
        return value;
    }

    private DelEnum(int value) {
        this.value = value;
    }

    public static DelEnum getByName(String name) {
        if (StrUtil.isBlank(name)) {
            return null;
        }
        try {
            return DelEnum.valueOf(name);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

}
