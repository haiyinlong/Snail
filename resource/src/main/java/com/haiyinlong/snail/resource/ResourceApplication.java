package com.haiyinlong.snail.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * ResourceApplication
 *
 * @author HaiYinLong
 * @version 2025/09/29 18:25
 **/
@SpringBootApplication
@EnableFeignClients
public class ResourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ResourceApplication.class, args);
    }
}
