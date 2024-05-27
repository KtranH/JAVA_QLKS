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
public class Sidebar {

    private final NewsService newsService;
    private final LoaiPhongService loaiPhongService;

    public Sidebar(NewsService newsService, LoaiPhongService loaiPhongService) {
        this.newsService = newsService;
        this.loaiPhongService = loaiPhongService;
    }

   ///lấy tất cả dữ liệu từ loại phòng//
    @GetMapping("/allCategory")
public String showAllCategory(Model model,@RequestParam(defaultValue = "0")int page,@RequestParam(defaultValue = "4")int size) {
    Pageable pageable=PageRequest.of(page,size);
        Page<LoaiPhong> pagecategories = loaiPhongService.getAllLoaiPhong(pageable);
        for (LoaiPhong category : pagecategories) {
            if (category.getAnh() != null && !category.getAnh().isEmpty()) {
                String[] images = category.getAnh().split("\\|");
                if (images.length > 0) {
                    category.setFirstImage(images[0]);
                }
            }
        }
        model.addAttribute("categories", pagecategories.getContent());
         model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pagecategories.getTotalPages());
        model.addAttribute("totalItems", pagecategories.getTotalElements());
        model.addAttribute("size", size);
        return "User/Rooms/allCategories";
    }
    ////load loại phòng cao cấp//
     @GetMapping("/caocap")
public String showLoaiPhongCaoCap(Model model,@RequestParam(defaultValue = "0")int page,@RequestParam(defaultValue = "4")int size) {
        Pageable pageable=PageRequest.of(page,size);
        Page<LoaiPhong> pagecaocap = loaiPhongService.getLoaiPhongCaoCap(pageable);
        for (LoaiPhong category : pagecaocap) {
            if (category.getAnh() != null && !category.getAnh().isEmpty()) {
                String[] images = category.getAnh().split("\\|");
                if (images.length > 0) {
                    category.setFirstImage(images[0]);
                }
            }
        }
        model.addAttribute("caocap", pagecaocap);
         model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pagecaocap.getTotalPages());
        model.addAttribute("totalItems", pagecaocap.getTotalElements());
        model.addAttribute("size", size);
        return "User/Rooms/highlevelCategories";
    }
    ////load loại phòng phổ thông//
     @GetMapping("/phothong")
public String showLoaiPhongPhoThong(Model model,@RequestParam(defaultValue = "0")int page,@RequestParam(defaultValue = "4")int size) {
         Pageable pageable=PageRequest.of(page,size);
        Page<LoaiPhong> pagephothong = loaiPhongService.getLoaiPhongPhoThong(pageable);
        for (LoaiPhong category : pagephothong) {
            if (category.getAnh() != null && !category.getAnh().isEmpty()) {
                String[] images = category.getAnh().split("\\|");
                if (images.length > 0) {
                    category.setFirstImage(images[0]);
                }
            }
        }
        model.addAttribute("phothong", pagephothong);
         model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pagephothong.getTotalPages());
        model.addAttribute("totalItems", pagephothong.getTotalElements());
        model.addAttribute("size", size);
        return "User/Rooms/commonCategories";
    }


     ////load loại phòng giá rẻ//
     @GetMapping("/giare")
public String showLoaiPhongGiaRe(Model model,@RequestParam(defaultValue = "0")int page,@RequestParam(defaultValue = "4")int size) {
         Pageable pageable=PageRequest.of(page,size);
        Page<LoaiPhong> pagegiare = loaiPhongService.getLoaiPhongGiaRe(pageable);
        for (LoaiPhong category : pagegiare) {
            if (category.getAnh() != null && !category.getAnh().isEmpty()) {
                String[] images = category.getAnh().split("\\|");
                if (images.length > 0) {
                    category.setFirstImage(images[0]);
                }
            }
        }
        model.addAttribute("giare", pagegiare);
         model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pagegiare.getTotalPages());
        model.addAttribute("totalItems", pagegiare.getTotalElements());
        model.addAttribute("size", size);
        return "User/Rooms/cheapCategories";
    }
  

}
