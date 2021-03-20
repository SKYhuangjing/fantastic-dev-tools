/*
 * Zenlayer.com Inc.
 * Copyright (c) 2014-2019 All Rights Reserved.
 */
package com.fantastic.platform.dev.common.dao.base;

import com.fantastic.platform.dev.common.dto.QuickSearchQueryDTO;

import java.util.List;

/**
 * @author sky
 * @date 2019-09-20 15:39:22
 * @version $ Id: BaseUidDAO.java, v 0.1  sky Exp $
 */
public interface BaseUidDAO<T, Q extends QuickSearchQueryDTO> extends BaseBizDAO<T, Q> {

    /**
     * 获取集合
     * @param uids  UID 集合
     * @return
     */
    List<T> listByUids(List<String> uids);

    /**
     * 获取对象
     * @param uid 对象 UID
     * @return
     */
    T findByUid(String uid);

    /**
     * 删除对象
     * @param uid 对象 UID
     * @return
     */
    boolean deleteByUid(String uid);
}
