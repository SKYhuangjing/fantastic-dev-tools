/*
 * Zenlayer.com Inc.
 * Copyright (c) 2014-2021 All Rights Reserved.
 */
package com.fantastic.platform.dev.service.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author sky
 * @date 2021-03-20 13:01:25
 * @version $ Id: BaseUidResponse.java, v 0.1  sky Exp $
 */
@Data
public class BaseUidResponse extends BaseBizResponse {

    @ApiModelProperty("ID")
    private String uid;

}
