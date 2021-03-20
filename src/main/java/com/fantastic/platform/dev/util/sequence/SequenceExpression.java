/*
 * Zenlayer.com Inc.
 * Copyright (c) 2014-2019 All Rights Reserved.
 */
package com.fantastic.platform.dev.util.sequence;

import com.fantastic.platform.dev.common.constant.SequenceConstant;

/**
 * @author sky
 * @date 2019-10-23 15:04:50
 * @version $ Id: SequenceExpression.java, v 0.1  sky Exp $
 */
public interface SequenceExpression {

    /**
     * 转化为字符串表达式
     * @return
     */
    String toExpression();

    /**
     * 获取下一个序列号
     * @return
     */
    String getNextNo();

    /**
     * 从数据库存储的表达式初始化
     * @param expressionStr
     */
    void init(String expressionStr);

    /**
     * 转化为字符串表达式, 并添加分隔符
     * @return
     */
    default String toDBExpression() {
        return String.join("", SequenceConstant.EXPRESSION_PREFIX, toExpression(), SequenceConstant.EXPRESSION_SUFFIX);
    }
}
