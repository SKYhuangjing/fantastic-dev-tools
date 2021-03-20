/*
 * Zenlayer.com Inc.
 * Copyright (c) 2014-2019 All Rights Reserved.
 */
package com.fantastic.platform.dev.util.sequence.impl;

import cn.hutool.core.util.StrUtil;
import com.fantastic.platform.dev.common.constant.SequenceConstant;
import com.fantastic.platform.dev.util.sequence.SequenceExpression;
import lombok.Data;

/**
 * @author sky
 * @date 2019-10-23 15:10:51
 * @version $ Id: SequenceDefaultExpression.java, v 0.1  sky Exp $
 */
@Data
public class SequenceDefaultExpression implements SequenceExpression {

    private String value;

    @Override
    public String getNextNo() {
        return value;
    }

    @Override
    public void init(String expressionStr) {
        String[] arr = StrUtil.split(expressionStr, SequenceConstant.EXPRESSION_SEPARATOR);
        value = arr[1];
    }

    @Override
    public String toExpression() {

        return StrUtil.join(SequenceConstant.EXPRESSION_SEPARATOR, SequenceConstant.EXPRESSION_METHOD_DEFAULT, value);
    }

}
