package com.example.java_qlks.controller.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.java_qlks.model.DichVu;
import com.example.java_qlks.service.DichVuService;
import com.example.java_qlks.service.NewsService;

@Controller
public class DichVuController {

    private final DichVuService dichVuService;
    private final NewsService newsService;
    

    @ModelAttribute
    public void addAttributes(Model model) {
        ArrayList<String[]> listLine = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new ClassPathResource("Files/activity.txt").getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lines = line.split("\\|");
                listLine.add(lines);
            }
            model.addAttribute("lisfftnews", newsService.readNewsItems());
            model.addAttribute("listline", listLine);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DichVuController(NewsService newsService1, DichVuService dichVuService1) {
        this.dichVuService = dichVuService1;
        this.newsService = newsService1;
    }

    @GetMapping("/DichVu")
    public String getDichVu(Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<DichVu> pageDichVu = dichVuService.getListDichVu(pageable);

        model.addAttribute("listDichVu", pageDichVu.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageDichVu.getTotalPages());
        model.addAttribute("totalItems", pageDichVu.getTotalElements());
        model.addAttribute("size", size);

        return "User/Dichvu";
    }

}
