package com.fantastic.platform.dev.biz;

import com.fantastic.platform.dev.common.dto.QuickSearchQueryDTO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author sky
 * @date 2019-10-18 14:28:27
 * @version $ Id: BaseManager.java, v 0.1  sky Exp $
 */
public interface BaseBizManager<E, Q extends QuickSearchQueryDTO> {

    /**
     * 根据 查询模型, 分页查询
     * @param queryDTO  查询模型
     * @return
     */
    PageInfo<E> listByPage(Q queryDTO);

    /**
     * 根据 查询模型, 查询
     * @param queryDTO  查询模型
     * @return
     */
    List<E> list(Q queryDTO);

    /**
     * 查询 信息
     * @param id
     * @return
     */
    E findById(Integer id);

    /**
     * 创建  信息
     * @param data  信息
     * @return
     */
    E save(E data);

    /**
     * 更新 信息
     * @param data 信息
     * @return
     */
    E update(E data);

    /**
     * 删除
     * @param id id
     * @return
     */
    boolean delete(Integer id);
}
