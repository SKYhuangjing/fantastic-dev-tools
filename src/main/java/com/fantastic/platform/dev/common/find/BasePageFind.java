/*
 * Zenlayer.com Inc.
 * Copyright (c) 2014-2021 All Rights Reserved.
 */
package com.fantastic.platform.dev.common.find;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author sky
 * @date 2021-03-17 17:46:25
 * @version $ Id: BasePageFind.java, v 0.1  sky Exp $
 */
@Data
public class BasePageFind extends BaseFind {

    @ApiModelProperty("分页码")
    private int pageNum = 1;

    @ApiModelProperty("单页数量")
    private int pageSize = 20;
}
