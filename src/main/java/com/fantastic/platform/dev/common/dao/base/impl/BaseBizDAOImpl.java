package com.fantastic.platform.dev.common.dao.base.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.fantastic.platform.dev.common.constant.ColumnConstant;
import com.fantastic.platform.dev.common.dao.base.BaseBizDAO;
import com.fantastic.platform.dev.common.dao.base.Converter;
import com.fantastic.platform.dev.common.dto.QuickSearchQueryDTO;
import com.fantastic.platform.dev.util.PageInfoUtil;
import com.fantastic.platform.dev.util.model.SortBy;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author sky
 * @date 2019-10-17 17:30:45
 * @version $ Id: BaseDelDAOImpl.java, v 0.1  sky Exp $
 */
public abstract class BaseBizDAOImpl<T, Q extends QuickSearchQueryDTO, D extends Mapper<T>> extends BaseDelDAOImpl<T, D>
        implements BaseBizDAO<T, Q> {

    protected Optional<String> getDOCreateTimeField() {
        return Optional.of(ColumnConstant.CREATE_TIME_FIELD);
    }

    protected Optional<String> getDOCreateByField() {
        return Optional.of(ColumnConstant.CREATE_BY_FIELD);
    }

    protected Optional<String> getDOUpdateTimeField() {
        return Optional.of(ColumnConstant.UPDATE_TIME_FIELD);
    }

    protected Optional<String> getDOUpdateByField() {
        return Optional.of(ColumnConstant.UPDATE_BY_FIELD);
    }

    @Override
    public <E> PageInfo<E> listByPage(Q queryDTO, Converter<E, T> converter) {
        PageHelper.startPage(queryDTO.getPageNum(), queryDTO.getPageSize());
        List<T> data = list(queryDTO);
        PageInfo<T> dataPageInfo = new PageInfo<>(data);
        List<E> convertedData = data.stream().map(converter::assemble).collect(Collectors.toList());
        PageInfo<E> convertDataPageInfo = new PageInfo<>();
        PageInfoUtil.copyPageInfoBasic(dataPageInfo, convertDataPageInfo, convertedData);
        return convertDataPageInfo;
    }

    @Override
    public List<T> list(Q queryDTO) {

        Example example = new Example(getDOClass());
        SortBy[] sortBies = queryDTO.getSortBies();

        if (sortBies != null) {
            example.setOrderByClause(Arrays.asList(sortBies).stream().filter(sortBy -> {
                String field = sortBy.getField();
                boolean exist = ReflectUtil.hasField(getDOClass(), field);
                if (!exist) {
                    throw new RuntimeException(StrUtil.format("{} has no field : {}", getDOClass().getSimpleName(), field));
                }
                return true;
            }).map(SortBy::toOrderString).collect(Collectors.joining(",")));
        }

        createDefaultCriteria(example);

        Criteria queryCriteria = processQueryValue(example, queryDTO.getQueryValue());
        if (queryCriteria != null && queryCriteria.isValid()) {
            example.and(queryCriteria);
        }

        queryCriteria = processExactValue(example, queryDTO.getExactValue());
        if (queryCriteria != null && queryCriteria.isValid()) {
            example.and(queryCriteria);
        }

        internalProcessListByQueryDTO(example, queryDTO);

        return getMapper().selectByExample(example);
    }

    @Override
    public boolean insert(T t) {
        getDOCreateTimeField().ifPresent(column -> ReflectUtil.setFieldValue(t, column, DateUtil.date()));
        getDOUpdateTimeField().ifPresent(column -> ReflectUtil.setFieldValue(t, column, DateUtil.date()));

        internalProcessInsert(t);

        return super.insert(t);
    }

    @Override
    public boolean update(T t) {
        getDOUpdateTimeField().ifPresent(column -> ReflectUtil.setFieldValue(t, column, DateUtil.date()));

        internalProcessUpdate(t);

        return super.update(t);
    }

    /**
     * 默认空实现
     * @param queryDTO
     */
    protected void internalProcessListByQueryDTO(Example example, Q queryDTO) {
    }

    /**
     * 默认空实现
     * @param t
     */
    protected void internalProcessInsert(T t) {
    }

    /**
     * 默认空实现
     * @param t
     */
    protected void internalProcessUpdate(T t) {
    }

}
