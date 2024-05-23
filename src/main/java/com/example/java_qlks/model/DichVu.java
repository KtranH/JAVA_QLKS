package com.example.java_qlks.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "DICHVU")
public class DichVu {
    @Id
    @Column(name = "MADV")
    private String MADV;
    
    @Column(name = "TENDV")
    private String TENDV;
    
    @Column(name = "GIADV")
    private float GIADV;

    @Column(name = "MOTA")
    private String MOTA;
    
    @Column(name = "HINHANH")
    private String HINHANH;

    @Column(name = "ISDELETE")
    private boolean ISDELETE;

    public DichVu() {
    }

    public DichVu(String MADV, String TENDV, float GIADV, String MOTA, String HINHANH, boolean ISDELETE) {
        this.MADV = MADV;
        this.TENDV = TENDV;
        this.GIADV = GIADV;
        this.MOTA = MOTA;
        this.HINHANH = HINHANH;
        this.ISDELETE = ISDELETE;
    }

    public String getMADV() {
        return MADV;
    }

    public void setMADV(String MADV) {
        this.MADV = MADV;
    }

    public String getTENDV() {
        return TENDV;
    }

    public void setTENDV(String TENDV) {
        this.TENDV = TENDV;
    }

    public float getGIADV() {
        return GIADV;
    }

    public void setGIADV(float GIADV) {
        this.GIADV = GIADV;
    }

    public String getMOTA() {
        return MOTA;
    }

    public void setMOTA(String MOTA) {
        this.MOTA = MOTA;
    }

    public String getHINHANH() {
        return HINHANH;
    }

    public void setHINHANH(String HINHANH) {
        this.HINHANH = HINHANH;
    }

    public boolean isISDELETE() {
        return ISDELETE;
    }

    public void setISDELETE(boolean ISDELETE) {
        this.ISDELETE = ISDELETE;
    }
}
