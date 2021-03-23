package com.fantastic.platform.dev.service.model.response;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @author sky
 * @date 2021-03-23 17:34:55
 * @version $ Id: BaseBizResponse.java, v 0.1  sky Exp $
 */
public class BaseBizResponse implements Serializable {

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
