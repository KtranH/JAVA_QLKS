package com.example.java_qlks.controller.Account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Login {
    @GetMapping("/login")
    public String login()
    {
        return "Account/Login";
    }
}
