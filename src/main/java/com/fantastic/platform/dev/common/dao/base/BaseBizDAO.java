/*
 * Zenlayer.com Inc.
 * Copyright (c) 2014-2019 All Rights Reserved.
 */
package com.fantastic.platform.dev.common.dao.base;

import com.fantastic.platform.dev.common.dto.QuickSearchQueryDTO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author sky
 * @date 2019-09-20 15:39:22
 * @version $ Id: BaseUidDAO.java, v 0.1  sky Exp $
 */
public interface BaseBizDAO<T, Q extends QuickSearchQueryDTO> extends BaseDAO<T> {

    /**
     * 根据查询模型, 分页查询
     * @param queryDTO 查询模型
     * @param  converter 类型转换器
     * @return
     */
    <E> PageInfo<E> listByPage(Q queryDTO, Converter<E, T> converter);

    /**
     * 根据查询模型, 查询
     * @param queryDTO 查询模型
     * @return
     */
    List<T> list(Q queryDTO);

}
