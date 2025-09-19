package com.haiyinlong.snail.authentication.infrastructure.dto;

import lombok.Data;

/**
 * ResponseDto
 *
 * @author HaiYinLong
 * @version 2025/09/16 12:30
 **/
@Data
public class ResponseDto<T> {
    private String code;
    private String message;
    private T data;

    public ResponseDto(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResponseDto<Void> error(String invalidParameter, String message) {
        return new ResponseDto<>(invalidParameter, message);
    }

    public static ResponseDto<Void> success() {
        return new ResponseDto<>("200", "success");
    }
}
