/*
 * Zenlayer.com Inc.
 * Copyright (c) 2014-2019 All Rights Reserved.
 */
package com.fantastic.platform.dev.common.dao.base.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.fantastic.platform.dev.common.constant.ColumnConstant;
import com.fantastic.platform.dev.common.dao.base.BaseUidDAO;
import com.fantastic.platform.dev.common.dao.model.enums.DelEnum;
import com.fantastic.platform.dev.common.dto.QuickSearchQueryDTO;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.Collections;
import java.util.List;

/**
 * @author sky
 * @date 2019-09-20 15:41:09
 * @version $ Id: BaseUidDAOImpl.java, v 0.1  sky Exp $
 */
public abstract class BaseUidDAOImpl<T, Q extends QuickSearchQueryDTO, D extends Mapper<T>> extends BaseBizDAOImpl<T, Q, D>
        implements BaseUidDAO<T, Q> {

    protected String getDOUIdField() {
        return ColumnConstant.UID_FIELD;
    }

    /**
     * 获取集合
     * @param uids  UID 集合
     * @return
     */
    @Override
    public List<T> listByUids(List<String> uids) {
        if (uids == null || uids.isEmpty()) {
            return Collections.emptyList();
        }
        Example example = new Example(this.getDOClass());
        Criteria criteria = createDefaultCriteria(example);
        criteria.andIn(getDOUIdField(), uids);

        internalProcessListByUids(example, uids);

        return getMapper().selectByExample(example);
    }

    @Override
    public T findByUid(String uid) {
        if (StrUtil.isBlank(uid)) {
            return null;
        }
        Example example = new Example(getDOClass());
        Criteria criteria = createDefaultCriteria(example);
        criteria.andEqualTo(getDOUIdField(), uid);

        internalProcessFindByUid(example, uid);

        T one = getMapper().selectOneByExample(example);
        return one;
    }

    @Override
    public boolean insert(T t) {
        Object uid = ReflectUtil.getFieldValue(t, getDOUIdField());
        if (uid == null) {
            ReflectUtil.setFieldValue(t, getDOUIdField(), IdUtil.simpleUUID());
        }
        return super.insert(t);
    }

    @Override
    public boolean deleteByUid(String uid) {
        if (StrUtil.isBlank(uid)) {
            return false;
        }
        if (getDODelField().isPresent()) {

            Example example = new Example(getDOClass());
            Criteria criteria = createDefaultCriteria(example);
            criteria.andEqualTo(getDOUIdField(), uid);

            T record = ReflectUtil.newInstanceIfPossible(getDOClass());

            ReflectUtil.setFieldValue(record, getDOUIdField(), uid);
            getDODelField().ifPresent(column -> ReflectUtil.setFieldValue(record, column, DelEnum.DEL.getValue()));
            getDODeleteTimeField().ifPresent(column -> ReflectUtil.setFieldValue(record, column, DateUtil.date()));
            int effectRows = getMapper().updateByExampleSelective(record, example);

            return effectRows > 0;
        } else {

            Example example = new Example(getDOClass());
            Criteria criteria = createDefaultCriteria(example);
            criteria.andEqualTo(getDOUIdField(), uid);

            int effectRows = this.getMapper().deleteByExample(example);
            return effectRows > 0;
        }
    }

    /**
     * 默认空实现
     * @param example
     * @param uid
     */
    protected void internalProcessFindByUid(Example example, String uid) {
    }

    /**
     * 默认空实现
     * @param example
     * @param uids
     */
    protected void internalProcessListByUids(Example example, List<String> uids) {
    }
}
