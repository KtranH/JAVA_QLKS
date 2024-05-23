package com.example.java_qlks.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.java_qlks.model.DichVu;

@Service
public interface DichVuService {
     List<DichVu> getListDichVu();
}
