package com.haiyinlong.snail.common.dto;

import java.util.List;

import lombok.Data;

/**
 * PageResponseDto
 *
 * @author HaiYinLong
 * @version 2025/10/11 10:36
 **/
@Data
public class PageResponseDto<T> {
    private Long total;
    private List<T> data;
}
