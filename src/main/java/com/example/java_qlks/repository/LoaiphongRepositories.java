package com.example.java_qlks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.java_qlks.model.LoaiPhong;


public interface LoaiphongRepositories extends JpaRepository<LoaiPhong, String> {
    ///lấy loại tiêu biểu//
@Query(value = "SELECT TOP 6 * FROM LOAIPHONG ORDER BY NEWID()", nativeQuery = true)
    List<LoaiPhong> findLoaiPhongTieuBieu();
//lấy loại cao cấp//
@Query(value = "SELECT * FROM LOAIPHONG WHERE TENLOAIPHONG LIKE N'%cao cấp%'",nativeQuery = true)
Page<LoaiPhong> findLoaiPhongCaoCap(Pageable pageable);
//lấy loại gia rẻ//
@Query(value = "SELECT * FROM LOAIPHONG WHERE TENLOAIPHONG LIKE N'%giá rẻ%'",nativeQuery = true)
Page<LoaiPhong> findLoaiPhongGiaRe(Pageable pageable);
//lấy loại phổ thông//
@Query(value = "SELECT * FROM LOAIPHONG WHERE TENLOAIPHONG LIKE N'%phổ thông%'",nativeQuery = true)
Page<LoaiPhong> findLoaiPhongPhoThong(Pageable pageable);
// /////tìm kiếm theo tên loại phòng, vị trí, sức chứa//
// @Query("SELECT p FROM LoaiPhong p WHERE p.tenLoaiPhong LIKE CONCAT('%',:query,'%') OR p.viTri LIKE CONCAT('%',:query,'%') OR str(p.sucChua) LIKE CONCAT('%',:query,'%')")
// List<LoaiPhong> findBySucchuaOrTenLoaiPhongOrViTri(@Param("query") String query);
    @Query("SELECT p FROM LoaiPhong p WHERE p.tenLoaiPhong LIKE CONCAT('%',:query,'%') OR p.viTri LIKE CONCAT('%',:query,'%') OR str(p.sucChua) LIKE CONCAT('%',:query,'%')")
    Page<LoaiPhong> findBySucchuaOrTenLoaiPhongOrViTri(@Param("query") String query, Pageable pageable);
//Load phòng theo giá từ cao đến thấp
@Query(value = "SELECT * FROM LOAIPHONG ORDER BY GIATHUE DESC", nativeQuery = true)
Page<LoaiPhong> findAllOrderByGiaThueDesc(Pageable pageable);
//Load phòng theo giá từ thấp đến cao
@Query(value = "SELECT * FROM LOAIPHONG ORDER BY GIATHUE ASC", nativeQuery = true)
Page<LoaiPhong> findAllOrderByGiaThueAsc(Pageable pageable);
}