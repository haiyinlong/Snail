package com.haiyinlong.snail.common.util;

import java.util.Objects;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

/**
 * RequestUtil
 *
 * @author HaiYinLong
 * @version 2025/09/19 09:43
 **/
public final class RequestUtil {
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes)Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
            .getRequest();
    }

    public static String getHeader(String headerName) {
        return getHttpServletRequest().getHeader(headerName);
    }

    public static Long getUserId() {
        return Long.parseLong(getHeader("user-id"));
    }

}
