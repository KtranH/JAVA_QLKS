package com.example.demo.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.checkUser;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class RentRoom {
    @Autowired
    public checkUser checkcookie;

    @PersistenceContext
    public EntityManager entityManager;

    @GetMapping("rent")
    @Transactional
    public String rent(HttpServletRequest request, Model model) 
    {
        //Kiểm tra đăng nhập
       String checkuser = checkcookie.checkLogin(request);
       List<Object[]> selectUser = checkcookie.selectUser(checkuser);
       model.addAttribute("user", selectUser);

       //Lấy các phòng khả dụng và phiếu đăng ký đặt trước
       Query query;
       List<Object[]> selectRoom;
       List<Object[]> selectPDKDT;
       
       query = entityManager.createNativeQuery("SELECT * FROM PHONG INNER JOIN LOAIPHONG ON PHONG.MALP = LOAIPHONG.MALP WHERE TRANGTHAI = 0");
       selectRoom = query.getResultList();

       query = entityManager.createNativeQuery("SELECT * FROM PHIEUDANGKY INNER JOIN PHONG on PHIEUDANGKY.MAPHONG = PHONG.MAPHONG WHERE TINHTRANG = ?");
       query.setParameter(1, "Đặt trước");
       selectPDKDT = query.getResultList();

       LocalDate today = LocalDate.now();

       model.addAttribute("today", today);
       model.addAttribute("selectRoom", selectRoom);
       model.addAttribute("selectPDKDT", selectPDKDT);

       return "Rent/RentRoom";
    }

    @PostMapping("formRent")
    @Transactional
    public String formRent(@RequestParam("datep") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate datep, @RequestParam("roomname") String roomname, HttpServletRequest request,  HttpSession session, Model model) 
    {
         //Kiểm tra đăng nhập
        String checkuser = checkcookie.checkLogin(request);
        List<Object[]> selectUser = checkcookie.selectUser(checkuser);

        LocalDate today = LocalDate.now();
        LocalDateTime now = LocalDateTime.now();
        if(today.isBefore(datep))
        {
            Query query;
            List<Object[]> checkRoom;
            query = entityManager.createNativeQuery("SELECT * FROM PHONG INNER JOIN LOAIPHONG ON PHONG.MALP = LOAIPHONG.MALP WHERE MAPHONG = ?");
            query.setParameter(1, roomname);
            checkRoom = query.getResultList();
            if(checkRoom.isEmpty())
            {
                model.addAttribute("errorRoomPDK", "Không tìm mã phòng này!");
                return "Rent/RentRoom";
            }
            else
            {
                long daysBetween = ChronoUnit.DAYS.between(today, datep);
                double sumPay = Double.parseDouble(checkRoom.get(0)[10].toString()) * daysBetween;

                query = entityManager.createNativeQuery("INSERT INTO PHIEUDANGKY (MAPHIEU,MANV,MAPHONG,NGAYDAT,TINHTRANG,TRAPHONG,THANHTOAN,TT_NHANPHONG) VALUES (?,?,?,?,?,?,?,?)");
                query.setParameter(1, "1111" + checkRoom.get(0)[10].toString() + now.getHour() + now.getMinute() + now.getMinute());
                query.setParameter(2, selectUser.get(0)[0]);
                query.setParameter(3, roomname);
                query.setParameter(4, today.toString());
                query.setParameter(5, "Đã xác nhận");
                query.setParameter(6, datep.toString());
                query.setParameter(7, sumPay);
                query.setParameter(8, "Đang đợi");
                query.executeUpdate();

                query = entityManager.createNativeQuery("UPDATE PHONG SET TRANGTHAI = 1 WHERE MAPHONG = ?");
                query.setParameter(1, roomname);
                query.executeUpdate();

                session.setAttribute("idphieu", "1111" + checkRoom.get(0)[10].toString() + now.getHour() + now.getMinute() + now.getMinute());
                return "redirect:/detailRent?idphieu=" + "1111" + checkRoom.get(0)[10].toString() + now.getHour() + now.getMinute() + now.getMinute();
            }
        }
        else
        {
            model.addAttribute("errorDayPDK", "Ngày tháng không hợp lệ!");
            return "Rent/RentRoom";
        }
    }
    
}
