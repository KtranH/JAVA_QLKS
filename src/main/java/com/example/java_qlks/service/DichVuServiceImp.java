package com.example.java_qlks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.java_qlks.model.DichVu;
import com.example.java_qlks.repositories.DichVuRepositories;

@Service
public class DichVuServiceImp implements DichVuService {

    private final DichVuRepositories dichVuRepositories;

    @Autowired
    public DichVuServiceImp(DichVuRepositories dichVuRepositories1) {
        this.dichVuRepositories = dichVuRepositories1;
    }

    @Override
    public Page<DichVu> getListDichVu(Pageable pageable) {
        return dichVuRepositories.findAll(pageable);
    }
}
