/*
 * Zenlayer.com Inc.
 * Copyright (c) 2014-2019 All Rights Reserved.
 */
package com.fantastic.platform.dev.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author sky
 * @date 2019-09-27 10:59:14
 * @version $ Id: QuickSearchQueryDTO.java, v 0.1  sky Exp $
 */
@Data
public class QuickSearchQueryDTO extends PageQueryDTO {

    @ApiModelProperty(value = "模糊查询的值", example = "sky")
    private String queryValue;

    @ApiModelProperty(value = "精确查询的值", example = "id:1")
    private String exactValue;
}
