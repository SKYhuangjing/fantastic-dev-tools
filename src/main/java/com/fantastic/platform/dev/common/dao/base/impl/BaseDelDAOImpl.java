package com.fantastic.platform.dev.common.dao.base.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.TypeUtil;
import com.fantastic.platform.dev.common.constant.ColumnConstant;
import com.fantastic.platform.dev.common.dao.base.BaseDelDAO;
import com.fantastic.platform.dev.common.dao.model.enums.DelEnum;
import com.github.pagehelper.util.StringUtil;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author sky
 * @date 2019-10-17 17:30:45
 * @version $ Id: BaseDelDAOImpl.java, v 0.1  sky Exp $
 */
public abstract class BaseDelDAOImpl<T, D extends Mapper<T>> extends BaseDAOImpl<T, D> implements BaseDelDAO<T> {

    protected Criteria processQueryValue(Example example, String queryValue) {
        Criteria queryCriteria = null;
        if (StringUtil.isNotEmpty(queryValue)) {
            List<String> queryColumns = getDOQueryColumns();

            queryCriteria = example.createCriteria();
            if (queryColumns != null && !queryColumns.isEmpty()) {
                for (String queryColumn : queryColumns) {
                    queryCriteria.orLike(queryColumn, addWildcard4Query() ? String.join("", "%", queryValue, "%") : queryValue);
                }
            }
        }
        return queryCriteria;
    }

    protected Criteria processExactValue(Example example, String exactValue) {
        Criteria queryCriteria = null;
        if (StrUtil.isNotBlank(exactValue)) {
            queryCriteria = example.createCriteria();
            String[] conditions = exactValue.split("[;]"), fieldValues, values;
            String fieldChar, valueChar;
            List<Object> inValues;
            Class valueClazz;

            for (String condition : conditions) {
                fieldValues = condition.split("[:]");
                if (fieldValues.length != 2) {
                    continue;
                }

                fieldChar = fieldValues[0];
                Field field = ReflectUtil.getField(getDOClass(), fieldChar);
                if (ObjectUtil.isEmpty(field)) {
                    continue;
                }

                valueChar = fieldValues[1];
                values = valueChar.split("[,]");
                valueClazz = TypeUtil.getClass(field);
                inValues = new ArrayList<>();
                for (String value : values) {
                    try {
                        inValues.add(Convert.convert(valueClazz, value));
                    } catch (Exception ex) {
                        //ignore
                    }
                }
                if (ObjectUtil.isEmpty(inValues)) {
                    continue;
                }

                if (inValues.size() == 1) {
                    queryCriteria.andEqualTo(fieldChar, inValues.get(0));
                } else {
                    queryCriteria.andIn(fieldChar, inValues);
                }
            }
        }

        return queryCriteria;
    }

    protected Optional<String> getDODelField() {
        return Optional.of(ColumnConstant.DEL_FIELD);
    }

    protected Optional<String> getDODeleteTimeField() {
        return Optional.of(ColumnConstant.DELETE_TIME_FIELD);
    }

    @Override
    protected Criteria createDefaultCriteria(Example example) {
        Criteria criteria = super.createDefaultCriteria(example);
        getDODelField().ifPresent(column -> criteria.andEqualTo(column, DelEnum.NOT_DEL.getValue()));
        return criteria;
    }

    @Override
    public List<T> list(String queryValue) {
        Example example = new Example(getDOClass());
        createDefaultCriteria(example);
        Criteria queryCriteria = processQueryValue(example, queryValue);
        if (queryCriteria != null) {
            example.and(queryCriteria);
        }

        internalProcessList(example, queryValue);

        return getMapper().selectByExample(example);
    }

    @Override
    public T findById(Integer id) {
        if (id == null) {
            return null;
        }

        Example example = new Example(getDOClass());
        Criteria criteria = createDefaultCriteria(example);
        criteria.andEqualTo(getDOIdColumn(), id);

        internalProcessFindById(example, id);

        T one = getMapper().selectOneByExample(example);
        return one;
    }

    @Override
    public boolean delete(Integer id) {
        if (id == null) {
            return false;
        }

        if (getDODelField().isPresent()) {

            Example example = new Example(getDOClass());
            Criteria criteria = createDefaultCriteria(example);
            criteria.andEqualTo(getDOIdColumn(), id);

            T record = ReflectUtil.newInstanceIfPossible(getDOClass());

            ReflectUtil.setFieldValue(record, getDOIdColumn(), id);
            getDODelField().ifPresent(column -> ReflectUtil.setFieldValue(record, column, DelEnum.DEL.getValue()));
            getDODeleteTimeField().ifPresent(column -> ReflectUtil.setFieldValue(record, column, DateUtil.date()));
            int effectRows = getMapper().updateByExampleSelective(record, example);

            return effectRows > 0;
        } else {
            return super.delete(id);
        }
    }

    /**
     * 默认空实现
     * @param example
     * @param id
     */
    protected void internalProcessFindById(Example example, Integer id) {
    }

    /**
     * 默认空实现
     * @param example
     * @param queryValue
     */
    protected void internalProcessList(Example example, String queryValue) {
    }
}
