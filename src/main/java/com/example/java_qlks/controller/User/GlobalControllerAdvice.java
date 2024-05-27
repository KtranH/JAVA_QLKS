package com.example.java_qlks.controller.User;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.java_qlks.model.LoaiPhong;
import com.example.java_qlks.service.LoaiPhongService;
import com.example.java_qlks.service.NewsService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.springframework.core.io.ClassPathResource;
import org.springframework.ui.Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ControllerAdvice
public class GlobalControllerAdvice {

    private final NewsService newsService;
    private final LoaiPhongService loaiPhongService;

    public GlobalControllerAdvice(NewsService newsService, LoaiPhongService loaiPhongService) {
        this.newsService = newsService;
        this.loaiPhongService = loaiPhongService;
    }

    @ModelAttribute
    public void addSharedAttributes(Model model,@RequestParam(defaultValue = "0")int page,@RequestParam(defaultValue = "4")int size) {
        Pageable pageable=PageRequest.of(page,size);
        Page<LoaiPhong> pageLoaitiebieu = loaiPhongService.getLoaiPhongCaoCap(pageable);
        ArrayList<String[]> listLine = new ArrayList<String[]>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new ClassPathResource("Files/activity.txt").getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lines = line.split("\\|");
                listLine.add(lines);
            } 
            
            model.addAttribute("listnews", newsService.readNewsItems());
            model.addAttribute("listline", listLine);
       model.addAttribute("loaitieubieu", pageLoaitiebieu);
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}

