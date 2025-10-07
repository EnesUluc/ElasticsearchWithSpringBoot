package com.blog.BlogSite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/")
    public String login(){
        return "login";
    }

    @GetMapping("/login-page")
    public String dashboard(){
        return "login-page";
    }

    @GetMapping("/access-denied")
    public String accessDenied(){
        return "access-denied";
    }
}
