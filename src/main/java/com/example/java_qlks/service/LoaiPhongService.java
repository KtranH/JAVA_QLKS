package com.example.java_qlks.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.java_qlks.model.LoaiPhong;

public interface LoaiPhongService {
    Page<LoaiPhong> getAllLoaiPhong( Pageable pageable);
    List<LoaiPhong> getLoaiPhongTieuBieu();
    Page<LoaiPhong> getLoaiPhongCaoCap(Pageable pageable);
    Page<LoaiPhong> getLoaiPhongPhoThong(Pageable pageable);
    Page<LoaiPhong> getLoaiPhongGiaRe(Pageable pageable);
    Page<LoaiPhong> getGiaPhongCaoThap(Pageable pageable);
    Page<LoaiPhong> getGiaPhongThapCao(Pageable pageable);
    Page<LoaiPhong>searchLoaiPhong(String query,Pageable pageable);


}