package com.example.java_qlks.controller.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BanDoController {

    public BanDoController(){

    }
    
    @GetMapping("/CoSo1")
    public String getCoSo1(){
        return "User/BanDo/Coso1";
    }

    @GetMapping("/CoSo2")
    public String getCoSo2(){
        return "User/BanDo/Coso2";
    }

    @GetMapping("/CoSo3")
    public String getCoSo3(){
        return "User/BanDo/Coso3";
    }
}
