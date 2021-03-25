package com.fantastic.platform.dev.util;

import cn.hutool.core.util.EnumUtil;
import cn.hutool.core.util.ObjectUtil;
import com.fantastic.platform.dev.common.dao.model.enums.base.BaseValueEnumInterface;

/**
 * @author sky
 * @date 2019-10-18 10:51:27
 * @version $ Id: EnumUtils.java, v 0.1  sky Exp $
 */
public class EnumUtils extends EnumUtil {

    public static <T, E extends BaseValueEnumInterface<T>> E getByValue(Class<E> enumClazz, T value) {
        try {
            if (value != null) {
                E[] enumArr = enumClazz.getEnumConstants();
                for (E enumVal : enumArr) {
                    if (ObjectUtil.equal(enumVal.getValue(), value)) {
                        return enumVal;
                    }

                }
            }
        } catch (IllegalArgumentException e) {
        }
        return null;
    }
}
