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
public class DetailEditRentRoom {
    @Autowired
    public checkUser checkcookie;

    @PersistenceContext
    public EntityManager entityManager;

    @GetMapping("detailEditRent")
    @Transactional
    public String detailEditRent(HttpServletRequest request, Model model) 
    {
        //Kiểm tra đăng nhập
       String checkuser = checkcookie.checkLogin(request);
       List<Object[]> selectUser = checkcookie.selectUser(checkuser);
       model.addAttribute("user", selectUser);

       //Lấy các phòng khả dụng và phiếu đăng ký đặt trước
       Query query;
       List<Object[]> selectPDK;
       
       query = entityManager.createNativeQuery("SELECT * FROM PHIEUDANGKY INNER JOIN PHONG ON PHIEUDANGKY.MAPHONG = PHONG.MAPHONG INNER JOIN LOAIPHONG ON PHONG.MALP = LOAIPHONG.MALP WHERE TINHTRANG = ?");
       query.setParameter(1, "Đã xác nhận");
       selectPDK = query.getResultList();

       model.addAttribute("selectPDK", selectPDK);

       return "Rent/EditDetailRent";
    }
}
