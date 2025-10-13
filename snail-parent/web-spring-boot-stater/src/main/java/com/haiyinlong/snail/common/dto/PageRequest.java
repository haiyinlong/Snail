package com.haiyinlong.snail.common.dto;

import lombok.Data;

/**
 * PageRequest
 *
 * @author HaiYinLong
 * @version 2025/10/11 10:32
 **/
@Data
public class PageRequest {
    private Integer pageNum;
    private Integer pageSize;
}
