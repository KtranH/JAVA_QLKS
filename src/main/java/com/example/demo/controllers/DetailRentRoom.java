package com.example.demo.controllers;

import java.time.LocalDate;
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
import jakarta.servlet.http.HttpSession;

@Controller
public class DetailRentRoom {
    @Autowired
    public checkUser checkcookie;

    @PersistenceContext
    public EntityManager entityManager;

    @GetMapping("detailRent")
    @Transactional
    public String detailRent(@RequestParam("idphieu") String idphieu, HttpServletRequest request, HttpSession session, Model model) 
    {
        //Kiểm tra đăng nhập
       String checkuser = checkcookie.checkLogin(request);
       List<Object[]> selectUser = checkcookie.selectUser(checkuser);
       model.addAttribute("user", selectUser);

       //Lấy các thông tin chi tiết của phiếu đăng ký
       Query query;
       List<Object[]> selectPDK;
       List<Object[]> selectCTPDK;
       
       if(idphieu == null)
       {
           idphieu = session.getAttribute("idphieu").toString();
       }

       query = entityManager.createNativeQuery("SELECT * FROM PHIEUDANGKY INNER JOIN PHONG ON PHIEUDANGKY.MAPHONG = PHONG.MAPHONG INNER JOIN LOAIPHONG ON PHONG.MALP = LOAIPHONG.MALP WHERE MAPHIEU = ?");
       query.setParameter(1, idphieu);
       selectPDK = query.getResultList();

       query = entityManager.createNativeQuery("SELECT * FROM CHITIET_PHIEUDANGKY INNER JOIN KHACHHANG ON CHITIET_PHIEUDANGKY.MAKH = KHACHHANG.MAKH WHERE MAPHIEU = ?");
       query.setParameter(1, idphieu);
       selectCTPDK = query.getResultList();

       model.addAttribute("selectPDK", selectPDK);
       model.addAttribute("selectCTPDK", selectCTPDK);

       return "Rent/DetailRentRoom";
    }

    //@GetMapping("/updateSL")
    // @Transactional
    //public void updatesl() {
    //    Query query;
    //   List<Object[]> selectCount;
    //    query = entityManager.createNativeQuery("SELECT LOAIPHONG.MALP, COUNT(*) AS SL FROM PHONG INNER JOIN LOAIPHONG ON PHONG.MALP = LOAIPHONG.MALP WHERE PHONG.MALP = LOAIPHONG.MALP GROUP BY LOAIPHONG.MALP");
    //    selectCount = query.getResultList();

    //    for (Object[] x : selectCount) 
    //    {
    //        query = entityManager.createNativeQuery("UPDATE LOAIPHONG SET SOLUONG = ? WHERE MALP = ?");
    //        query.setParameter(1, x[1]);
    //        query.setParameter(2, x[0]);
    //        query.executeUpdate();
    //    }
    //
    // }
    
}
