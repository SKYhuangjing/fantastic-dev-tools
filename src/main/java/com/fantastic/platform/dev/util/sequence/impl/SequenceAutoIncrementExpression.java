/*
 * Zenlayer.com Inc.
 * Copyright (c) 2014-2019 All Rights Reserved.
 */
package com.fantastic.platform.dev.util.sequence.impl;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.fantastic.platform.dev.common.constant.SequenceConstant;
import com.fantastic.platform.dev.util.sequence.SequenceExpression;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sky
 * @date 2019-10-23 15:16:36
 * @version $ Id: SequenceAutoIncrementExpression.java, v 0.1  sky Exp $
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SequenceAutoIncrementExpression implements SequenceExpression {

    private Integer length;

    private char filledChar;

    private Integer gap;

    private Integer current;

    @Override
    public String toExpression() {
        return StrUtil.join(SequenceConstant.EXPRESSION_SEPARATOR, SequenceConstant.EXPRESSION_METHOD_AUTO_INCREMENT, length, filledChar,
                gap, current);
    }

    @Override
    public String getNextNo() {
        current = current + gap;
        String next = StrUtil.fillBefore(String.valueOf(current), filledChar, length);
        return next;
    }

    @Override
    public void init(String expressionStr) {
        String[] arr = StrUtil.split(expressionStr, SequenceConstant.EXPRESSION_SEPARATOR);
        length = NumberUtil.parseInt(arr[1]);
        filledChar = arr[2].charAt(0);
        gap = NumberUtil.parseInt(arr[3]);
        current = NumberUtil.parseInt(arr[4]);
    }
}
