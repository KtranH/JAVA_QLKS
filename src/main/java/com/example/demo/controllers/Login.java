package com.example.demo.controllers;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class Login {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("login")
    public String ShowLogin(HttpServletResponse response) {
        Cookie cookie = new Cookie("username", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return "Account/Login";
    }
    @PostMapping("authlogin")
    @Transactional
    public String AuthLogin(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpServletResponse response) {
       PasswordEncoder encoder = new BCryptPasswordEncoder();
       Query query = entityManager.createNativeQuery("SELECT * FROM NHANVIEN WHERE EMAIL = ?");
       query.setParameter(1, username);
       List<Object[]> checkNV = query.getResultList();
       if(!checkNV.isEmpty())
       {
            for (Object[] x : checkNV) {
                if(encoder.matches(password, x[8].toString()))
                {
                    Cookie cookie = new Cookie("username", username);
                    cookie.setPath("/");
                    cookie.setMaxAge(-1);
                    cookie.setHttpOnly(true);
                    response.addCookie(cookie);
                    return "redirect:/";
                }
            }
       }
       model.addAttribute("errorlogin","Bạn đã nhập sai email hoặc mật khẩu");
       return "Account/Login";
    }
    
}
