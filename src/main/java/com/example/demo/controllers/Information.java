package com.example.demo.controllers;

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
public class Information {
    @Autowired
    public checkUser checkcookie;

    @PersistenceContext
    public EntityManager entityManager;

    @GetMapping("information")
    @Transactional
    public String information(HttpServletRequest request, Model model) 
    {
        //Kiểm tra đăng nhập
       String checkuser = checkcookie.checkLogin(request);
       List<Object[]> selectUser = checkcookie.selectUser(checkuser);
       model.addAttribute("user", selectUser);
       
       //Lấy các khách hàng, phiếu đăng ký, hóa đơn đã thanh toán
       Query query;
       List<Object[]> selectKH;
       List<Object[]> selectPDK;
       List<Object[]> selectHD;

       query = entityManager.createNativeQuery("SELECT * FROM KHACHHANG");
       selectKH = query.getResultList();

       query = entityManager.createNativeQuery("SELECT * FROM PHIEUDANGKY INNER JOIN PHONG ON PHIEUDANGKY.MAPHONG = PHONG.MAPHONG INNER JOIN LOAIPHONG ON PHONG.MALP = LOAIPHONG.MALP");
       selectPDK = query.getResultList();

       query = entityManager.createNativeQuery("SELECT * FROM HOADON WHERE NGAYLAP IS NOT NULL");
       selectHD = query.getResultList();

       model.addAttribute("selectKH", selectKH);
       model.addAttribute("selectPDK", selectPDK);
       model.addAttribute("selectHD", selectHD);

       return "Information/Information";
    }
}
