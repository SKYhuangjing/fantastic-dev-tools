package com.fantastic.platform.dev.biz.impl;

import com.fantastic.platform.dev.biz.BaseBizManager;
import com.fantastic.platform.dev.common.dao.base.BaseBizDAO;
import com.fantastic.platform.dev.common.dao.base.Converter;
import com.fantastic.platform.dev.common.dto.QuickSearchQueryDTO;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sky
 * @date 2019-10-18 14:28:27
 * @version $ Id: BaseManager.java, v 0.1  sky Exp $
 */
public abstract class BaseBizManagerImpl<E, Q extends QuickSearchQueryDTO, T, D extends BaseBizDAO<T, Q>> implements BaseBizManager<E, Q> {

    @Autowired
    protected D dao;

    private Converter converter;

    /**
     * 转化工具
     * @return
     */
    public Converter<E, T> converter() {
        if (converter == null) {
            converter = (Converter<E, T>) data -> internalDo2bo(data);
        }
        return converter;
    }

    protected E internalDo2bo(T data) {
        if (data != null) {
            E convertData = do2bo(data);
            return convertData;
        }
        return null;
    }

    /**
     * DO 模型 转 BO 模型
     * @param data 有可能是 null
     * @return
     */
    public abstract E do2bo(T data);

    /**
     * BO 模型 转 DO 模型
     * @param data 有可能是 null
     * @return
     */
    public abstract T bo2do(E data);

    @Override
    public PageInfo<E> listByPage(Q queryDTO) {
        return dao.listByPage(queryDTO, converter());
    }

    @Override
    public List<E> list(Q queryDTO) {
        List<T> data = dao.list(queryDTO);
        return data.stream().map(this::internalDo2bo).collect(Collectors.toList());
    }

    @Override
    public E findById(Integer id) {
        T data = dao.findById(id);
        return data != null ? internalDo2bo(data) : null;
    }

    @Override
    public E save(E data) {
        T recordDO = bo2do(data);
        recordDO = internalProcessSave(recordDO);
        boolean result = dao.insert(recordDO);
        return result ? internalDo2bo(recordDO) : null;
    }

    @Override
    public E update(E data) {
        T recordDO = bo2do(data);
        recordDO = internalProcessUpdate(recordDO);
        boolean result = dao.update(recordDO);
        return result ? internalDo2bo(recordDO) : null;
    }

    @Override
    public boolean delete(Integer id) {
        return dao.delete(id);
    }

    protected T internalProcessSave(T t) {
        return t;
    }

    protected T internalProcessUpdate(T t) {
        return t;
    }
}
