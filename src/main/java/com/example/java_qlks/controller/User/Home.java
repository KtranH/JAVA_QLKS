package com.example.java_qlks.controller.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Home {
    @GetMapping("/")
     public String home()
    {
        return "User/Home";
    }
}
