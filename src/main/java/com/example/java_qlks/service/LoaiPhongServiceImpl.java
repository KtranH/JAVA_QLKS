package com.example.java_qlks.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.java_qlks.model.LoaiPhong;
import com.example.java_qlks.repository.LoaiphongRepositories;

@Service
public class LoaiPhongServiceImpl implements LoaiPhongService {

    private final LoaiphongRepositories loaiphongRepositories;

    @Autowired
    public LoaiPhongServiceImpl(LoaiphongRepositories loaiphongRepositories) {
        this.loaiphongRepositories = loaiphongRepositories;
    }
///lấy tất cả loại phòng//
    @Override
    public Page<LoaiPhong> getAllLoaiPhong(Pageable pageable) {
        return loaiphongRepositories.findAll(pageable);
    }
    ///lấy loại tiêu biểu//
  @Override
public List<LoaiPhong> getLoaiPhongTieuBieu(){
    return loaiphongRepositories.findLoaiPhongTieuBieu();
}
///lấy loại cao cấp
  @Override
public Page<LoaiPhong> getLoaiPhongCaoCap(Pageable pageable) {
    return loaiphongRepositories.findLoaiPhongCaoCap(pageable);
}
//lấy loại giá rẻ//
  @Override
public Page<LoaiPhong> getLoaiPhongGiaRe(Pageable pageable) {
    return loaiphongRepositories.findLoaiPhongGiaRe(pageable);
}
//lấy loại phổ thông//
  @Override
public Page<LoaiPhong> getLoaiPhongPhoThong(Pageable pageable) {
    return loaiphongRepositories.findLoaiPhongPhoThong(pageable);
}

//Tìm kiếm//
@Override
 public Page<LoaiPhong> searchLoaiPhong(String query,Pageable pageable) {
        return loaiphongRepositories.findBySucchuaOrTenLoaiPhongOrViTri(query,pageable);
    }
//Gia phong tu cao den thap
@Override
public Page<LoaiPhong> getGiaPhongCaoThap(Pageable pageable) {
    return loaiphongRepositories.findAllOrderByGiaThueDesc(pageable);
    }
//Gia phong tu thap den cao
@Override
public Page<LoaiPhong> getGiaPhongThapCao(Pageable pageable) {
    return loaiphongRepositories.findAllOrderByGiaThueAsc(pageable);
    }
}