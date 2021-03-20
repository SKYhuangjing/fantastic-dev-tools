package com.fantastic.platform.dev.common.dao.model.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author sky
 * @date 2019-10-17 18:40:03
 * @version $ Id: BaseIdBO.java, v 0.1  sky Exp $
 */
@Data
public class BaseIdBO {

    @ApiModelProperty(value = "对象ID", example = "1")
    private Integer id;

}
