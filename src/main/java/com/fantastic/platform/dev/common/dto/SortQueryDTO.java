/*
 * Zenlayer.com Inc.
 * Copyright (c) 2014-2019 All Rights Reserved.
 */
package com.fantastic.platform.dev.common.dto;

import com.fantastic.platform.dev.util.model.SortBy;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author sky
 * @date 2019-09-27 10:58:39
 * @version $ Id: PageQueryDTO.java, v 0.1  sky Exp $
 */
@Data
public class SortQueryDTO implements Serializable {

    @ApiModelProperty(value = "排序")
    private SortBy[] sortBies;

}
