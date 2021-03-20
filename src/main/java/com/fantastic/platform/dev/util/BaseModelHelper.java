/*
 * Zenlayer.com Inc.
 * Copyright (c) 2014-2021 All Rights Reserved.
 */
package com.fantastic.platform.dev.util;

import cn.hutool.core.util.ReflectUtil;
import com.fantastic.platform.dev.common.dao.base.Converter;
import com.github.pagehelper.PageInfo;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sky
 */
public class BaseModelHelper {

    public static <E, T> E copy(T source, Class<E> targetClazz) {
        return copy(source, null, targetClazz);
    }

    public static <E, T> E copy(T source, E target, Class<E> targetClazz) {
        if (source != null) {
            if (target == null) {
                target = ReflectUtil.newInstanceIfPossible(targetClazz);
            }

            XBeanUtils.copy(source, target);
        }

        return target;
    }

    public static <T, D> List<D> buildList(List<T> data, Converter<D, T> converter) {
        if (CollectionUtils.isEmpty(data)) {
            return new ArrayList<>();
        }
        return data.stream().map(v -> converter.assemble(v)).collect(Collectors.toList());
    }

    public static <T, D> PageInfo<D> buildPageInfo(PageInfo<T> dataPageInfo, Converter<D, T> converter) {
        List<D> records = buildList(dataPageInfo.getList(), converter);

        PageInfo<D> pageInfo = new PageInfo<>();
        PageInfoUtil.copyPageInfoBasic(dataPageInfo, pageInfo, records);
        return pageInfo;
    }
}