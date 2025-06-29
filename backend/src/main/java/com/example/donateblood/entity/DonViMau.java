package com.example.donateblood.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "don_vi_mau")
public class DonViMau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer unitId;

    private String thanhPhanMau; // Máu toàn phần, Hồng cầu, Tiểu cầu...
    private LocalDate ngayNhapKho;
    private LocalDate hanSuDung;

    @ManyToOne
    @JoinColumn(name = "nhom_mau_id", nullable = false)
    private NhomMau nhomMau;
}