package com.haiyinlong.snail.resource.controller;

import java.util.Enumeration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * DemoController
 *
 * @author HaiYinLong
 * @version 2025/09/18 17:41
 **/
@Slf4j
@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping
    public String demo(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            log.info("Header: {} = {}", name, request.getHeader(name));
        }
        return "demo";
    }
}
