package com.example.java_qlks.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.java_qlks.model.DichVu;

@Service
public interface DichVuService {
     Page<DichVu> getListDichVu(Pageable pageable);
}
