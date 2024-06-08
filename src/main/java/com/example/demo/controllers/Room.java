package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.checkUser;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.servlet.http.HttpServletRequest;


@Controller
public class Room {
    @Autowired
    public checkUser checkcookie;

    @PersistenceContext
    public EntityManager entityManager;

    @GetMapping("room")
    @Transactional
    public String room(HttpServletRequest request, Model model) 
    {
        //Kiểm tra đăng nhập
       String checkuser = checkcookie.checkLogin(request);
       List<Object[]> selectUser = checkcookie.selectUser(checkuser);
       model.addAttribute("user", selectUser);
       
       //Lấy các phòng
       Query query;
       List<Object[]> selectCate;
       List<Object[]> selectRoom;

       query = entityManager.createNativeQuery("SELECT * FROM LOAIPHONG");
       selectCate = query.getResultList();
       query = entityManager.createNativeQuery("SELECT * FROM PHONG INNER JOIN LOAIPHONG ON PHONG.MALP = LOAIPHONG.MALP");
       selectRoom = query.getResultList();

       model.addAttribute("selectCate", selectCate);
       model.addAttribute("selectRoom", selectRoom);
       return "Room/Room";
    }
}
