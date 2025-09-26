package com.haiyinlong.snail.authorization.infrastructure.dto;

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

    public ResponseDto(String code, T data) {
        this.code = code;
        this.data = data;
    }

    public static ResponseDto<Void> error(String invalidParameter, String message) {
        return new ResponseDto<>(invalidParameter, message);
    }

    public static ResponseDto<Void> success() {
        return new ResponseDto<>("200", "success");
    }

    public static <T> ResponseDto<T> of(T token) {
        return new ResponseDto<>("200", token);
    }
}
