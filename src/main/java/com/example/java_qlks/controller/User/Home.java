// package com.example.java_qlks.controller.User;

// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// import java.util.ArrayList;
// import java.util.List;

// import org.springframework.core.io.ClassPathResource;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.util.NumberUtils;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.RequestMapping;

// import com.example.java_qlks.model.LoaiPhong;
// import com.example.java_qlks.service.LoaiPhongService;
// import com.example.java_qlks.service.NewsService;

// import io.micrometer.core.instrument.util.StringEscapeUtils;
// import io.micrometer.core.ipc.http.HttpSender.Request;

// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;
// import org.springframework.web.bind.annotation.RestController;


// @RestController
// @Controller
// @RequestMapping("/api/loaiphong")
// public class Home {
//     //////---------ĐOẠN CODE NÀY ĐÃ ĐƯỢC XỬ LÝ BÊN GlobalControllerAdvice(ĐỂ DÙNG CHUNG DỮ LIỆU) NÊN KHÔNG CẦN GỌI LẠI//
//     private final NewsService newsService;
//     private final LoaiPhongService loaiPhongService;
//     public Home(NewsService newsService,LoaiPhongService loaiPhongService) {
//         this.newsService = newsService;
//         this.loaiPhongService=loaiPhongService;
//     }
//     // @ModelAttribute
//     //  public void addAttribute(Model model)
//     // {
//     //     ArrayList<String[]> listLine = new ArrayList<String[]>();
//     //         // Đọc tệp từ classpath
//     //     try (BufferedReader reader = new BufferedReader(new InputStreamReader(new ClassPathResource("Files/activity.txt").getInputStream()))) {
//     //         String line;
//     //         while ((line = reader.readLine()) != null) {
//     //             String[] lines = line.split("\\|");
//     //             listLine.add(lines);
//     //         } 
            
//     //             // Truyền danh sách dòng vào model
//     //             model.addAttribute("listnews", newsService.readNewsItems());
//     //             model.addAttribute("listline", listLine);
//     //            
//     //     }
//     //     catch (Exception e) {
//     //         System.out.println(e.toString());
//     //     }
//     // }
//       //Home 
//     @GetMapping("/")
//     public String home(Model model){
//          List<LoaiPhong> loaitieubieu = loaiPhongService.getLoaiPhongTieuBieu();
//         for (LoaiPhong category : loaitieubieu) {
//             if (category.getAnh() != null && !category.getAnh().isEmpty()) {
//                 String[] images = category.getAnh().split("\\|");
//                 if (images.length > 0) {
//                     category.setFirstImage(images[0]);
//                 }
//             }
//         }
//        model.addAttribute("loaitieubieu", loaitieubieu);
//         return "User/Home";
//     }   
// @GetMapping("/search")
//     public ResponseEntity<List<LoaiPhong>> searchLoaiPhong(
//             @RequestParam(required = false) Integer succhua,
//             @RequestParam(required = false) String tenloai,
//             @RequestParam(required = false) String vitri) {
//         List<LoaiPhong> result = loaiPhongService.searchLoaiPhong(succhua, tenloai, vitri);
//         return ResponseEntity.ok(result);
//     }
 
// // public ResponseEntity<String> search(@RequestParam String search) {
// //     String output;
// //     String searchTerm = search;
// //     List<LoaiPhong> data = null;

// //     if (isNumeric(searchTerm)) {
// //         data = loaiPhongService.findBySucChua(Integer.parseInt(searchTerm));
// //     } else {
// //         data = loaiPhongService.findByTenLoaiPhongOrViTri(searchTerm, searchTerm);
// //     }
    
// //     if (data.size() > 0) {
// //         output = "<ul>";
// //         for (LoaiPhong row : data) {
// //             if (isNumeric(searchTerm)) {
// //                 output += "<li><a href='/search/result/" + row.getSucChua() + "'>" + row.getSucChua() + "</a></li>";
// //             } else {
// //                 if (row.getTenLoaiPhong() != null) {
// //                     output += "<li><a href='/search/result/" + row.getTenLoaiPhong() + "'> " + row.getTenLoaiPhong() + "</a></li>";
// //                 }
// //                 if (row.getViTri() != null) {
// //                     output += "<li><a href='/search/result/" + row.getViTri() + "'>" + row.getViTri() + "</a></li>";
// //                 }
// //             }
// //         }
// //         output += "</ul>";
// //     } else {
// //         output = "<li style='list-style: none;'>Không có kết quả</li>";
// //     }
// //     return new ResponseEntity<>(output, HttpStatus.OK);
// // }

// // // Phương thức kiểm tra xem chuỗi có thể chuyển đổi thành số hay không
// private boolean isNumeric(String str) {
//     if (str == null) {
//         return false;
//     }
//     try {
//         Integer.parseInt(str);
//     } catch (NumberFormatException nfe) {
//         return false;
//     }
//     return true;
// }


    
    
    

// }


package com.example.java_qlks.controller.User;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_qlks.model.LoaiPhong;
import com.example.java_qlks.service.LoaiPhongService;
import com.example.java_qlks.service.NewsService;

@Controller
public class Home {
    private final NewsService newsService;
    private final LoaiPhongService loaiPhongService;

    public Home(NewsService newsService, LoaiPhongService loaiPhongService) {
        this.newsService = newsService;
        this.loaiPhongService = loaiPhongService;
    }
   @GetMapping("/")
public String home(Model model){
    
    List<LoaiPhong> loaiPhongList = loaiPhongService.getLoaiPhongTieuBieu(); 
    for (LoaiPhong category : loaiPhongList) {
        if (category.getAnh() != null && !category.getAnh().isEmpty()) {
            String[] images = category.getAnh().split("\\|");
            if (images.length > 0) {
                category.setFirstImage(images[0]);
            }
        }
    }
    model.addAttribute("loaitieubieu", loaiPhongList);
    return "User/Home";
}
    //tìm kiếm sức chứa,vị trí,tên loại phòng//
@GetMapping("/search")
 public String search(@RequestParam(required = false) String query,
                      @RequestParam(defaultValue = "0") int page,
                      @RequestParam(defaultValue = "4") int size,
                      Model model) {
     Pageable pageable = PageRequest.of(page, size);
     Page<LoaiPhong> resultsPage = loaiPhongService.searchLoaiPhong(query, pageable);
     List<LoaiPhong> results = resultsPage.getContent();

     for (LoaiPhong category : results) {
         if (category.getAnh() != null && !category.getAnh().isEmpty()) {
             String[] images = category.getAnh().split("\\|");
             if (images.length > 0) {
                 category.setFirstImage(images[0]);
             }
         }
     }

     model.addAttribute("results", results);
     model.addAttribute("currentPage", page);
     model.addAttribute("totalPages", resultsPage.getTotalPages());
      model.addAttribute("totalItems", resultsPage.getTotalElements());
        model.addAttribute("size", size);

     return "User/Rooms/searchResults";
 }
   
}


