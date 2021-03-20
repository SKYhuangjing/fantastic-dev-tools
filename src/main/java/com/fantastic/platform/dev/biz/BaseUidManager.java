package com.fantastic.platform.dev.biz;

import com.fantastic.platform.dev.common.dto.QuickSearchQueryDTO;

/**
 * @author sky
 * @date 2019-10-18 14:28:27
 * @version $ Id: BaseManager.java, v 0.1  sky Exp $
 */
public interface BaseUidManager<E, Q extends QuickSearchQueryDTO> extends BaseBizManager<E, Q> {

    /**
     * 查询 信息
     * @param uid
     * @return
     */
    E findByUid(String uid);

    /**
     * 删除
     * @param uid
     * @return
     */
    boolean deleteByUid(String uid);

}
