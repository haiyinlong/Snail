package com.haiyinlong.snail.authentication.domain.exception;

import java.io.Serial;

/**
 * BusinessException
 *
 * @author HaiYinLong
 * @version 2025/09/16 12:22
 **/
public class BusinessException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1809826686641253935L;
    private final String errorCode;

    public BusinessException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
