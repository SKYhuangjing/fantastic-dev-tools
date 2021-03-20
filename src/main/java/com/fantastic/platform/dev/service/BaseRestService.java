/*
 * Zenlayer.com Inc.
 * Copyright (c) 2014-2021 All Rights Reserved.
 */
package com.fantastic.platform.dev.service;

import com.fantastic.platform.dev.common.dto.QuickSearchQueryDTO;
import com.fantastic.platform.dev.util.model.ResultInfo;
import com.github.pagehelper.PageInfo;

/**
 * @author sky
 * @date 2021-03-20 12:45:18
 * @version $ Id: BaseRestService.java, v 0.1  sky Exp $
 */
public interface BaseRestService<T, D extends QuickSearchQueryDTO> {

    ResultInfo<PageInfo<T>> page(D request);

    ResultInfo<T> queryById(String uid);
}
