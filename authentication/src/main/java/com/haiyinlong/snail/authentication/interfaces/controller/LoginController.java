package com.haiyinlong.snail.authentication.interfaces.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * LoginController
 *
 * @author HaiYinLong
 * @version 2025/09/19 10:54
 **/
@Controller
public class LoginController {

    /**
     * 显示登录页面
     */
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("loginError", "用户名或密码错误");
        }
        return "login";
    }
}
