package com.fantastic.platform.dev.biz.impl;

import com.fantastic.platform.dev.biz.BaseUidManager;
import com.fantastic.platform.dev.common.dao.base.BaseUidDAO;
import com.fantastic.platform.dev.common.dto.QuickSearchQueryDTO;

/**
 * @author sky
 * @date 2019-11-06 14:53:06
 * @version $ Id: BaseUidManagerImpl.java, v 0.1  sky Exp $
 */
public abstract class BaseUidManagerImpl<E, Q extends QuickSearchQueryDTO, T, D extends BaseUidDAO<T, Q>>
        extends BaseBizManagerImpl<E, Q, T, D> implements BaseUidManager<E, Q> {

    @Override
    public E findByUid(String uid) {
        T data = dao.findByUid(uid);
        return data != null ? this.do2bo(data) : null;
    }

    @Override
    public boolean deleteByUid(String uid) {
        return dao.deleteByUid(uid);
    }
}
