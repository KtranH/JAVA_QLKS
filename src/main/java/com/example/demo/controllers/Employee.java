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
public class Employee {

    @Autowired
    public checkUser checkcookie;

    @PersistenceContext
    public EntityManager entityManager;

    @GetMapping("employee")
    @Transactional
    public String employee(HttpServletRequest request, Model model) 
    {
        //Kiểm tra đăng nhập
       String checkuser = checkcookie.checkLogin(request);
       List<Object[]> selectUser = checkcookie.selectUser(checkuser);
       model.addAttribute("user", selectUser);
       
       //Lấy các nhân viên
       Query query;
       List<Object[]> selectNV;

       query = entityManager.createNativeQuery("SELECT * FROM NHANVIEN INNER JOIN NHOMQUYEN ON NHANVIEN.MANHOM = NHOMQUYEN.MANHOM INNER JOIN PHANQUYEN ON NHOMQUYEN.MAPQ = PHANQUYEN.MAPQ");
       selectNV = query.getResultList();

       model.addAttribute("selectNV", selectNV);

       return "Employee/Employee";
    }
}
