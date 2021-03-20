/*
 * Zenlayer.com Inc.
 * Copyright (c) 2014-2019 All Rights Reserved.
 */
package com.fantastic.platform.dev.util.sequence;

import cn.hutool.core.util.StrUtil;
import com.fantastic.platform.dev.common.constant.SequenceConstant;
import com.fantastic.platform.dev.util.sequence.impl.SequenceAutoIncrementExpression;
import com.fantastic.platform.dev.util.sequence.impl.SequenceDefaultExpression;

/**
 * @author sky
 * @date 2019-10-23 15:31:09
 * @version $ Id: SequenceUtil.java, v 0.1  sky Exp $
 */
public class SequenceUtil {

    public static SequenceExpression toExpression(String expression) {
        SequenceExpression sequenceExpression = null;
        if (StrUtil.startWith(expression, SequenceConstant.EXPRESSION_METHOD_AUTO_INCREMENT)) {
            sequenceExpression = new SequenceAutoIncrementExpression();
            sequenceExpression.init(expression);
        }
        if (StrUtil.startWith(expression, SequenceConstant.EXPRESSION_METHOD_DEFAULT)) {
            sequenceExpression = new SequenceDefaultExpression();
            sequenceExpression.init(expression);
        }
        return sequenceExpression;
    }

    public static SequenceExpression buildAutoIncrementExpression(Integer length, char filledChar, Integer gap, Integer current) {
        SequenceAutoIncrementExpression sequenceExpression = new SequenceAutoIncrementExpression();
        sequenceExpression.setLength(length);
        sequenceExpression.setFilledChar(filledChar);
        sequenceExpression.setGap(gap);
        sequenceExpression.setCurrent(current);
        return sequenceExpression;
    }

    public static SequenceExpression buildDefaultExpression(String value) {
        SequenceDefaultExpression sequenceExpression = new SequenceDefaultExpression();
        sequenceExpression.setValue(value);
        return sequenceExpression;
    }
}
