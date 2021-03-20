package com.fantastic.platform.dev.util.model;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author sky
 * @date 2019-10-28 17:49:58
 * @version $ Id: SortBy.java, v 0.1  sky Exp $
 */
@Data
public class SortBy {

    @ApiModelProperty(value = "排序的字段", example = "id")
    private String field;

    @ApiModelProperty(value = "1:升序, 其他:降序", example = "1")
    private Integer asc;

    public static final String ASC = "asc";

    public static final String DESC = "desc";

    public String toOrderString() {
        return String.join(" ", StrUtil.toUnderlineCase(field), asc == 1 ? ASC : DESC);
    }
}
