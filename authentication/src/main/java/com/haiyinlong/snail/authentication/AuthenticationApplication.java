package com.haiyinlong.snail.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * AuthenticationApplication
 *
 * @author HaiYinLong
 * @version 2025/09/15 16:01
 **/
@SpringBootApplication
@EnableWebSecurity
public class AuthenticationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationApplication.class, args);
    }
}
