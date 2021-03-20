/*
 * Zenlayer.com Inc.
 * Copyright (c) 2014-2021 All Rights Reserved.
 */
package com.fantastic.platform.dev.common.dao.base.impl;

import com.fantastic.platform.dev.common.dao.base.BaseDAO;
import com.fantastic.platform.dev.common.dao.base.Converter;
import com.fantastic.platform.dev.util.PageInfoUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseDAOImpl<T, D extends Mapper<T>> implements BaseDAO<T> {

    protected Class<T> getDOClass() {
        Class<T> tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return tClass;
    }

    /**
     * 对应的 ID 字段
     * @return
     */
    protected String getDOIdColumn() {
        return "id";
    }

    /**
     * 搜索时, 是否需要前后添加通配符
     * @return
     */
    protected boolean addWildcard4Query() {
        return true;
    }

    /**
     * 获取搜索对应的字段
     * @return
     */
    protected abstract List<String> getDOQueryColumns();

    @Autowired
    private D mapper;

    protected D getMapper() {
        return mapper;
    }

    @Override
    public List<T> list(String queryValue) {
        if (StringUtil.isEmpty(queryValue)) {
            return mapper.selectAll();
        } else {
            Example example = new Example(getDOClass());
            List<String> queryColumns = getDOQueryColumns();

            Example.Criteria criteria = example.createCriteria();
            if (queryColumns != null && !queryColumns.isEmpty()) {
                for (String queryColumn : queryColumns) {
                    criteria.orLike(queryColumn, addWildcard4Query() ? String.join("", "%", queryValue, "%") : queryValue);
                }
            }
            return mapper.selectByExample(example);
        }
    }

    @Override
    public <E> PageInfo<E> listByPage(String queryValue, int pageNum, int pageSize, Converter<E, T> converter) {
        PageHelper.startPage(pageNum, pageSize);
        List<T> data = list(queryValue);
        PageInfo<T> dataPageInfo = new PageInfo<T>(data);
        List<E> convertedData = data.stream().map(converter::assemble).collect(Collectors.toList());
        PageInfo<E> convertDataPageInfo = new PageInfo<>();
        PageInfoUtil.copyPageInfoBasic(dataPageInfo, convertDataPageInfo, convertedData);
        return convertDataPageInfo;
    }

    @Override
    public T findById(Integer id) {
        Example example = new Example(getDOClass());
        example.createCriteria().andEqualTo(getDOIdColumn(), id);

        T one = mapper.selectOneByExample(example);
        return one;
    }

    @Override
    public boolean insert(T t) {
        int effectRows = mapper.insertSelective(t);
        return effectRows > 0;
    }

    @Override
    public boolean update(T t) {
        int effectRows = mapper.updateByPrimaryKeySelective(t);
        return effectRows > 0;
    }

    @Override
    public boolean delete(Integer id) {
        int effectRows = mapper.deleteByPrimaryKey(id);
        return effectRows > 0;
    }
}