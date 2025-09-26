package com.haiyinlong.snail.authentication.interfaces.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.haiyinlong.snail.authentication.application.client.CreateRegisteredClientService;
import com.haiyinlong.snail.authentication.application.client.dto.CreateRegisteredClientRequest;

import lombok.RequiredArgsConstructor;

/**
 * RegisteredClientController
 *
 * @author HaiYinLong
 * @version 2025/09/26 14:08
 **/
@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class RegisteredClientController {
    private final CreateRegisteredClientService createRegisteredClientService;

    @PostMapping("")
    public void createRegisteredClient(@RequestBody CreateRegisteredClientRequest createRequest) {
        createRegisteredClientService.createRegisteredClient(createRequest);
    }
}
