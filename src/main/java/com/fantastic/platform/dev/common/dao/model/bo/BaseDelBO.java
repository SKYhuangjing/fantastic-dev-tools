package com.fantastic.platform.dev.common.dao.model.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author sky
 * @date 2019-10-17 18:40:03
 * @version $ Id: BaseIdBO.java, v 0.1  sky Exp $
 */
@Data
public class BaseDelBO extends BaseIdBO {

    @ApiModelProperty(value = "删除时间")
    private Date deleteTime;

    @ApiModelProperty(value = "删除状态, 1:删除, 0:未删除", example = "1")
    private Integer del;

}
