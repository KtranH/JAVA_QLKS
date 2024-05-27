package com.example.java_qlks.controller.User;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.java_qlks.model.LoaiPhong;
import com.example.java_qlks.service.LoaiPhongService;
import com.example.java_qlks.service.NewsService;
@Controller
public class Classify {
    private final NewsService newsService;
    private final LoaiPhongService loaiPhongService;

    public Classify(NewsService newsService, LoaiPhongService loaiPhongService) {
        this.newsService = newsService;
        this.loaiPhongService = loaiPhongService;
    }
    // Load tất cả các phòng theo giá từ cao đến thấp với phân trang
@GetMapping("/giaCaodenThap")
public String showRoomsSortedByPriceDesc(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "4") int size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<LoaiPhong> pageGiaCao = loaiPhongService.getGiaPhongCaoThap(pageable);
    for (LoaiPhong category : pageGiaCao) {
        if (category.getAnh() != null && !category.getAnh().isEmpty()) {
            String[] images = category.getAnh().split("\\|");
            if (images.length > 0) {
                category.setFirstImage(images[0]);
            }
        }
    }
    model.addAttribute("giaCao", pageGiaCao);
    model.addAttribute("currentPage", page);
    model.addAttribute("totalPages", pageGiaCao.getTotalPages());
    model.addAttribute("totalItems", pageGiaCao.getTotalElements());
    model.addAttribute("size", size);
    return "User/Rooms/sortByPriceDesc";
}

// Load tất cả các phòng theo giá từ thấp đến cao với phân trang
@GetMapping("/giaThapdenCao")
public String showRoomsSortedByPriceAsc(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "4") int size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<LoaiPhong> pageGiaThap = loaiPhongService.getGiaPhongThapCao(pageable);
    for (LoaiPhong category : pageGiaThap) {
        if (category.getAnh() != null && !category.getAnh().isEmpty()) {
            String[] images = category.getAnh().split("\\|");
            if (images.length > 0) {
                category.setFirstImage(images[0]);
            }
        }
    }
    model.addAttribute("giaThap", pageGiaThap);
    model.addAttribute("currentPage", page);
    model.addAttribute("totalPages", pageGiaThap.getTotalPages());
    model.addAttribute("totalItems", pageGiaThap.getTotalElements());
    model.addAttribute("size", size);
    return "User/Rooms/sortByPriceAsc";
}

}
