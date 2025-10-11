package com.haiyinlong.snail.authorization.application.dto;

import lombok.Data;

/**
 * QueryUserTeamResponse
 *
 * @author HaiYinLong
 * @version 2025/10/11 09:55
 **/
@Data
public class QueryUserTeamResponse {

    private String name;
    private String logo;
    private String plan;

    public QueryUserTeamResponse(String name, String logo, String plan) {
        this.name = name;
        this.logo = logo;
        this.plan = plan;
    }
}
