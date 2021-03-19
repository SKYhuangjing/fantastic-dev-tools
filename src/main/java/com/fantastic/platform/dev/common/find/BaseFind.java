/*
 * Zenlayer.com Inc.
 * Copyright (c) 2014-2021 All Rights Reserved.
 */
package com.fantastic.platform.dev.common.find;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author sky
 * @date 2021-03-17 17:45:57
 * @version $ Id: BaseFind.java, v 0.1  sky Exp $
 */
@Data
public class BaseFind implements Serializable {

    @ApiModelProperty("模糊搜索字段")
    private String fuzzyQueryValue;
}
