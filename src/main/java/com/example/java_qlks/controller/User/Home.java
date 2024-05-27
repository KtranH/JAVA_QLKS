package com.example.java_qlks.controller.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.java_qlks.service.NewsService;

@Controller
public class Home {
    private final NewsService newsService;
    public Home(NewsService newsService) {
        this.newsService = newsService;
    }
    @GetMapping("/")
     public String home(Model model)
    {
        ArrayList<String[]> listLine = new ArrayList<String[]>();
            // Đọc tệp từ classpath
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new ClassPathResource("Files/activity.txt").getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lines = line.split("\\|");
                listLine.add(lines);
            } 
                model.addAttribute("listnews", newsService.readNewsItems());
                // Truyền danh sách dòng vào model
                model.addAttribute("listline", listLine);
                // Trả về tên view
                return "User/Home";
        }
        catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.toString());
            return "Error";
        }
    }
}
