package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class checkUser {

    @PersistenceContext
    public EntityManager entityManager;

    public String checkLogin(HttpServletRequest request)
    {
        String user = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    user = cookie.getValue().toString();
                    break;
                }
            }
        }
        return user;
    }
    public List<Object[]> selectUser(String checkuser)
    {
        Query query;
        List<Object[]> selectUser;
        query = entityManager.createNativeQuery("SELECT * FROM NHANVIEN WHERE EMAIL = ?");
        query.setParameter(1, checkuser);
        selectUser = query.getResultList();
        return selectUser;
    }
}
