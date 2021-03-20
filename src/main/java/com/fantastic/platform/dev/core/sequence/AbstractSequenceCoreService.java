/*
 * Zenlayer.com Inc.
 * Copyright (c) 2014-2019 All Rights Reserved.
 */
package com.fantastic.platform.dev.core.sequence;

import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.fantastic.platform.dev.common.constant.SequenceConstant;
import com.fantastic.platform.dev.util.sequence.BaseSequence;
import com.fantastic.platform.dev.util.sequence.SequenceExpression;
import com.fantastic.platform.dev.util.sequence.SequenceUtil;

import java.util.List;

/**
 * @author sky
 * @date 2019-10-23 13:30:11
 * @version $ Id: AbstractSequenceCoreServiceImpl.java, v 0.1  sky Exp $
 */
public abstract class AbstractSequenceCoreService<T extends BaseSequence> implements SequenceCoreService {

    /**
     * 通过 名称 获取 Sequence
     * @param name
     * @return
     */
    public abstract T findByName(String name);

    /**
     * 创建 Sequence
     * @param name
     * @param expression
     * @return
     */
    public abstract T buildSequence(String name, String expression);

    /**
     * 保存或 更新 Sequence
     * @param sequence
     * @return
     */
    public abstract boolean saveOrUpdate(T sequence);

    @Override
    public String getNextNo(String name, List<SequenceExpression> defaultExpressions) {
        T sequenceDO = findByName(name);

        if (sequenceDO == null) {
            StringBuilder exp = new StringBuilder();
            defaultExpressions.stream().map(SequenceExpression::toDBExpression).forEach(exp::append);
            sequenceDO = buildSequence(name, exp.toString());
        }

        List<String> expressionList = ReUtil.findAll(SequenceConstant.EXPRESSION_PATTERN, sequenceDO.getExpression(), 0);

        StringBuilder nextBuilder = StrUtil.builder();

        StringBuilder expressionBuilder = StrUtil.builder();

        for (int index = 0; index < expressionList.size(); index++) {

            String expression = expressionList.get(index);

            SequenceExpression sequenceExpression = SequenceUtil.toExpression(expression);

            nextBuilder.append(sequenceExpression.getNextNo());
            expressionBuilder.append(sequenceExpression.toDBExpression());
        }
        sequenceDO.setExpression(expressionBuilder.toString());

        saveOrUpdate(sequenceDO);
        return nextBuilder.toString();
    }
}
