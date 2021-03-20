/*
 * Zenlayer.com Inc.
 * Copyright (c) 2014-2021 All Rights Reserved.
 */
package com.fantastic.platform.dev.common.dao.base;

import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BaseDAO<T> {
    List<T> list(String queryValue);

    <E> PageInfo<E> listByPage(String queryValue, int pageNum, int pageSize, Converter<E, T> converter);

    T findById(Integer id);

    boolean insert(T t);

    boolean update(T t);

    boolean delete(Integer id);
}