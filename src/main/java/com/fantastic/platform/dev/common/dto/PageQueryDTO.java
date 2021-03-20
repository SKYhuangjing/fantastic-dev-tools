/*
 * Zenlayer.com Inc.
 * Copyright (c) 2014-2019 All Rights Reserved.
 */
package com.fantastic.platform.dev.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author sky
 * @date 2019-09-27 10:58:39
 * @version $ Id: PageQueryDTO.java, v 0.1  sky Exp $
 */
@Data
public class PageQueryDTO extends SortQueryDTO implements Serializable {

    @ApiModelProperty(value = "页码数", example = "1")
    private Integer pageNum = 1;

    @ApiModelProperty(value = "每页大小", example = "10")
    private Integer pageSize = 10;
}
