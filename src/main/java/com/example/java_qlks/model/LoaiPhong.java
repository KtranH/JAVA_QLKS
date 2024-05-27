package com.example.java_qlks.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.persistence.Table;

@Entity
@Table(name = "LOAIPHONG")
public class LoaiPhong {

    @Id
    @Column(name = "MALP")
    private String maLP;
    
    @Column(name = "TENLOAIPHONG")
    private String tenLoaiPhong;
    
    @Column(name = "MOTA")
    private String moTa;
    
    @Column(name = "SUCCHUA")
    private int sucChua;

    @Column(name = "SOLUONG")
    private int soLuong;

    @Column(name = "ANH")
    private String anh;

    @Column(name = "GIATHUE")
    private float giaThue;

    @Column(name = "VITRI")
    private String viTri;
      @Column(name = "ISDELETE")
    private Boolean isDelete;
      @Column(name = "DIENTICH")
    private String dienTich;
      @Column(name = "TIENICH")
    private String tienIch;
      @Column(name = "NOITHAT")
    private String noiThat;
      @Column(name = "QUIDINH")
    private String quyDinh;

    @Transient
    private String firstImage;

    // Constructors, Getters, and Setters
    public LoaiPhong() {}
    public LoaiPhong(String maLP, String tenLoaiPhong, String moTa, int sucChua, int soLuong, String anh, float giaThue,
            String viTri, Boolean isDelete, String dienTich, String tienIch, String noiThat, String quyDinh,
            String firstImage) {
        this.maLP = maLP;
        this.tenLoaiPhong = tenLoaiPhong;
        this.moTa = moTa;
        this.sucChua = sucChua;
        this.soLuong = soLuong;
        this.anh = anh;
        this.giaThue = giaThue;
        this.viTri = viTri;
        this.isDelete = isDelete;
        this.dienTich = dienTich;
        this.tienIch = tienIch;
        this.noiThat = noiThat;
        this.quyDinh = quyDinh;
        this.firstImage = firstImage;
    }

    


    public String getMaLP() {
        return maLP;
    }

    // public LoaiPhong(String maLP, String tenLoaiPhong, String moTa, int sucChua, int soLuong, String anh, float giaThue,
    //         String viTri, String firstImage) {
    //     this.maLP = maLP;
    //     this.tenLoaiPhong = tenLoaiPhong;
    //     this.moTa = moTa;
    //     this.sucChua = sucChua;
    //     this.soLuong = soLuong;
    //     this.anh = anh;
    //     this.giaThue = giaThue;
    //     this.viTri = viTri;
    //     this.firstImage = firstImage;
    // }




    public void setMaLP(String maLP) {
        this.maLP = maLP;
    }

    public String getTenLoaiPhong() {
        return tenLoaiPhong;
    }

    public void setTenLoaiPhong(String tenLoaiPhong) {
        this.tenLoaiPhong = tenLoaiPhong;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getSucChua() {
        return sucChua;
    }

    public void setSucChua(int sucChua) {
        this.sucChua = sucChua;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public float getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(float giaThue) {
        this.giaThue = giaThue;
    }

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }

    public String getFirstImage() {
        return firstImage;
    }

    public void setFirstImage(String firstImage) {
        this.firstImage = firstImage;
    }
    public Boolean getIsDelete() {
        return isDelete;
    }
    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }
    public String getDienTich() {
        return dienTich;
    }
    public void setDienTich(String dienTich) {
        this.dienTich = dienTich;
    }
    public String getTienIch() {
        return tienIch;
    }
    public void setTienIch(String tienIch) {
        this.tienIch = tienIch;
    }
    public String getNoiThat() {
        return noiThat;
    }
    public void setNoiThat(String noiThat) {
        this.noiThat = noiThat;
    }
    public String getQuyDinh() {
        return quyDinh;
    }
    public void setQuyDinh(String quyDinh) {
        this.quyDinh = quyDinh;
    }
    
}
