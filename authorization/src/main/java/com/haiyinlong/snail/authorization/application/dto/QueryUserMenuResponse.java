package com.haiyinlong.snail.authorization.application.dto;

import java.util.List;

import lombok.Data;

/**
 * QueryUserMenuResponse
 *
 * @author HaiYinLong
 * @version 2025/10/10 17:34
 **/
@Data
public class QueryUserMenuResponse {

    private String title;
    private String url;
    private String icon;
    private Integer type;
    private List<QueryUserMenuResponse> items;
}
