package com.haiyinlong.snail.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * AuthorizationApplication
 *
 * @author HaiYinLong
 * @version 2025/09/18 17:43
 **/
@SpringBootApplication
@EnableFeignClients
public class AuthorizationApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthorizationApplication.class, args);
    }
}
