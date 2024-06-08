package com.example.demo.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.checkUser;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class Invoice {
    @Autowired
    public checkUser checkcookie;

    @PersistenceContext
    public EntityManager entityManager;

    @GetMapping("invoice")
    @Transactional
    public String invoice(HttpServletRequest request, Model model) 
    {
        //Kiểm tra đăng nhập
       String checkuser = checkcookie.checkLogin(request);
       List<Object[]> selectUser = checkcookie.selectUser(checkuser);
       model.addAttribute("user", selectUser);

       //Lấy các phòng khả dụng và phiếu đăng ký đặt trước
       Query query;
       List<Object[]> selectInc;
       
       query = entityManager.createNativeQuery("SELECT * FROM HOADON INNER JOIN PHIEUDANGKY ON HOADON.MAPHIEU = PHIEUDANGKY.MAPHIEU INNER JOIN PHONG ON PHIEUDANGKY.MAPHONG = PHONG.MAPHONG INNER JOIN LOAIPHONG ON PHONG.MALP = LOAIPHONG.MALP WHERE NGAYLAP IS NULL");
       selectInc = query.getResultList();

       model.addAttribute("selectInc", selectInc);

       return "Invoice/Invoice";
    }
}
