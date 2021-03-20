package com.fantastic.platform.dev.common.dao.model.bo;

/**
 * @author sky
 * @date 2019-11-06 15:34:07
 * @version $ Id: BaseCreateByBO.java, v 0.1  sky Exp $
 */
public interface BaseUpdateByBO {

    String getUpdateBy();

    void setUpdateBy(String updateBy);

    String getUpdater();

    void setUpdater(String updater);
}
