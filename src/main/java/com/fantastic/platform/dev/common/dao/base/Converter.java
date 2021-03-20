/*
 * Zenlayer.com Inc.
 * Copyright (c) 2014-2021 All Rights Reserved.
 */
package com.fantastic.platform.dev.common.dao.base;

@FunctionalInterface
public interface Converter<E, T> {
    E assemble(T data);
}