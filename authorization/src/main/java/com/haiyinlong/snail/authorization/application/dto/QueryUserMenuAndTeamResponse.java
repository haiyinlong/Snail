package com.haiyinlong.snail.authorization.application.dto;

import java.util.List;

import lombok.Data;

/**
 * QueryUserMenuAndTeamResponse
 *
 * @author HaiYinLong
 * @version 2025/10/11 09:54
 **/
@Data
public class QueryUserMenuAndTeamResponse {

    private List<QueryUserTeamResponse> teams;
    private List<QueryUserMenuResponse> navMain;

    public QueryUserMenuAndTeamResponse(List<QueryUserTeamResponse> teamResponses,
        List<QueryUserMenuResponse> menuResponses) {
        teams = teamResponses;
        navMain = menuResponses;
    }
}
