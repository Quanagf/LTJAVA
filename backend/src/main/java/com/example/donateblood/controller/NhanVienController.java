package com.example.donateblood.controller;

import com.example.donateblood.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/nhan-vien")
public class NhanVienController {

    @Autowired
    private NhanVienService nhanVienService;

    @GetMapping("/yeu-cau-mau")
    public ResponseEntity<Void> quanLyYeuCauMau() {
        nhanVienService.quanLyYeuCauMau();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/kho-mau")
    public ResponseEntity<Void> quanLyKhoMau() {
        nhanVienService.quanLyKhoMau();
        return ResponseEntity.ok().build();
    }
}