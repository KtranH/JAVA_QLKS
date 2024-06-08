package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.checkUser;



@Controller
public class Home {
    
    @Autowired
    public checkUser checkcookie;

    @PersistenceContext
    public EntityManager entityManager;

    @GetMapping("/")
    @Transactional
    public String AddHome(HttpServletRequest request, Model model)
    {
        String checkuser = null;
        Query query;
        checkuser = checkcookie.checkLogin(request);
        if(checkuser == "" || checkuser == null)
        {
            return "redirect:/login";
        }
        else
        {
            //Kiểm tra người dùng
            List<Object[]> selectUser = checkcookie.selectUser(checkuser);
            //Gán giá trị vào trong view
            model.addAttribute("user", selectUser);

            //Kiểm tra phiếu đăng ký
            List<Object[]> selectPDK;
            query = entityManager.createNativeQuery("SELECT * FROM PHIEUDANGKY INNER JOIN PHONG ON PHIEUDANGKY.MAPHONG = PHONG.MAPHONG INNER JOIN LOAIPHONG ON PHONG.MALP = LOAIPHONG.MALP WHERE TINHTRANG = ?");
            query.setParameter(1, "Đặt trước");
            selectPDK = query.getResultList();
             //Gán giá trị vào trong view
            model.addAttribute("pdk", selectPDK);

            //Kiểm tra những loại phòng tiêu biểu
            List<Object[]> selectRoom;
            List<Object[]> checkroom;
            query = entityManager.createNativeQuery("SELECT * FROM LOAIPHONG");
            selectRoom = query.getResultList();
            query = entityManager.createNativeQuery("SELECT * FROM PHIEUDANGKY INNER JOIN PHONG ON PHIEUDANGKY.MAPHONG = PHONG.MAPHONG INNER JOIN LOAIPHONG ON PHONG.MALP = LOAIPHONG.MALP");
            checkroom = query.getResultList();
            int SLroom = selectRoom.size();
            int[] Arrcount = new int[SLroom];
            int i = 0;
            for (Object[] select : selectRoom) 
            {
                int count = 0;
                for (Object[] check : checkroom) 
                {
                    if(select[0].toString().equals(check[12].toString()))
                    {
                        count = count + 1;
                    }
                }
                Arrcount[i] = count;
                i = i + 1;
            }
             //Gán giá trị vào trong view
             model.addAttribute("selectRoom", selectRoom);
             model.addAttribute("count", Arrcount);

            return "Home/HomeAdmin";
        }
    }
}
