package com.haiyinlong.snail.authorization.application;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.haiyinlong.snail.authorization.application.dto.QueryMenuPageResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class QueryMenuPageServiceTest {
    @Autowired
    QueryMenuPageService queryMenuPageService;

    @Test
    void queryPage() {
        List<QueryMenuPageResponse> queryMenuPageResponses = queryMenuPageService.queryPage();
        log.info("{}", queryMenuPageResponses);
    }
}
