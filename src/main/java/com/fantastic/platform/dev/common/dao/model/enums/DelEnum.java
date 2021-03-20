/*
 * Zenlayer.com Inc.
 * Copyright (c) 2014-2019 All Rights Reserved.
 */
package com.fantastic.platform.dev.common.dao.model.enums;

import com.fantastic.platform.dev.common.dao.model.enums.base.BaseValueEnumInterface;

/**
 * @author sky
 * @date 2019-09-18 14:46:22
 * @version $ Id: DelEnum.java, v 0.1  sky Exp $
 */
public enum DelEnum implements BaseValueEnumInterface<Integer> {
    /**
     * 删除
     */
    DEL(1),
    /**
     *未删除
     */
    NOT_DEL(0);

    private int value;

    @Override
    public Integer getValue() {
        return value;
    }

    DelEnum(int value) {
        this.value = value;
    }
}

