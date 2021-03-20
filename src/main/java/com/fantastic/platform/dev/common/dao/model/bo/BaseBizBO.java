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
public class BaseBizBO extends BaseDelBO {

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}
