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
public enum YesNoEnum implements BaseValueEnumInterface<Integer> {
    /**
     * 是
     */
    YES(1),
    /**
     *否
     */
    NO(0);

    private int value;

    @Override
    public Integer getValue() {
        return value;
    }

    YesNoEnum(int value) {
        this.value = value;
    }
}

