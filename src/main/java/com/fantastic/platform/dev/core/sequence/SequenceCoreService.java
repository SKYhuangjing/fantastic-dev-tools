/*
 * Zenlayer.com Inc.
 * Copyright (c) 2014-2019 All Rights Reserved.
 */
package com.fantastic.platform.dev.core.sequence;

import com.fantastic.platform.dev.util.sequence.SequenceExpression;

import java.util.List;

/**
 * @author sky
 * @date 2019-10-23 13:28:40
 * @version $ Id: SequenceCoreService.java, v 0.1  sky Exp $
 */
public interface SequenceCoreService {

    /**
     * 获取下一个序列号
     * @param name 序列号名称
     * @param defaultExpressions 当序列号不存在数据库时, 默认的序列号表达式
     * @return
     */
    String getNextNo(String name, List<SequenceExpression> defaultExpressions);
}
