package com.example.donateblood.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "lich_su_hien_mau")
public class LichSuHienMau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer donationId;

    private LocalDate ngayHien;
    private int soLuong; // ml
    private String diaDiem;

    // Lịch sử này thuộc về một thành viên
    @ManyToOne
    @JoinColumn(name = "thanh_vien_id", nullable = false)
    private ThanhVien thanhVien;

    // Một lần hiến máu có thể đáp ứng một yêu cầu máu (nếu có)
    @OneToOne
    @JoinColumn(name = "yeu_cau_id")
    private YeuCauMau yeuCauMau;
}