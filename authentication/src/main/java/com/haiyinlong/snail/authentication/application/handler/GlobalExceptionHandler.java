package com.haiyinlong.snail.authentication.application.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.haiyinlong.snail.authentication.domain.exception.BusinessException;
import com.haiyinlong.snail.common.dto.ResponseDto;

/**
 * GlobalExceptionHandler
 *
 * @author HaiYinLong
 * @version 2025/09/16 12:19
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseDto<Void>> handleIllegalArgument(IllegalArgumentException e) {
        ResponseDto<Void> error = ResponseDto.error("INVALID_PARAMETER", e.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ResponseDto<Void>> handleBusinessException(BusinessException e) {
        ResponseDto<Void> error = ResponseDto.error(e.getErrorCode(), e.getMessage());
        return ResponseEntity.badRequest().body(error);
    }
}
