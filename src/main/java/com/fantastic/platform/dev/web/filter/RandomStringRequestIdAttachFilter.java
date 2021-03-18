/*
 * Zenlayer.com Inc.
 * Copyright (c) 2014-2021 All Rights Reserved.
 */
package com.fantastic.platform.dev.web.filter;

import cn.hutool.core.util.RandomUtil;
import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RandomStringRequestIdAttachFilter extends OncePerRequestFilter {
    private static final String MDC_REQUEST_ID_PLACE_HOLDER = "xRequestId";
    private              String headerName;

    public RandomStringRequestIdAttachFilter(String projectName) {
        this.headerName = "x-" + projectName + "-request-id";
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String requestId = RandomUtil.randomString(32);
        response.setHeader(this.headerName, requestId);
        MDC.put(MDC_REQUEST_ID_PLACE_HOLDER, requestId);
        filterChain.doFilter(request, response);
    }
}
