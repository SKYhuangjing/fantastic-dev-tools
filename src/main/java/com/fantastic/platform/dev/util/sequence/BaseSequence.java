package com.fantastic.platform.dev.util.sequence;

/**
 * @author sky
 * @date 2019-12-23 12:59:40
 * @version $ Id: BaseSequence.java, v 0.1  sky Exp $
 */
public interface BaseSequence {

    /**
     * 获取表达式
     * @return
     */
    String getExpression();

    /**
     * 获取名称
     * @return
     */
    String getName();

    /**
     * 设置表达式
     * @param expression
     * @return
     */
    void setExpression(String expression);

    /**
     * 设置名称
     * @param name
     * @return
     */
    void setName(String name);
}
