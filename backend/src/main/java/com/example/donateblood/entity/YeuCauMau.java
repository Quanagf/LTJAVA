package com.example.donateblood.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "yeu_cau_mau")
public class YeuCauMau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer requestId;

    private int soLuong; // đơn vị ml hoặc unit
    private String trangThai; // Đang chờ, Đã duyệt, Hoàn thành
    private boolean laKhanCap;
    private String viTri; // Bệnh viện, trung tâm...

    // Một yêu cầu máu cần một nhóm máu cụ thể
    @ManyToOne
    @JoinColumn(name = "nhom_mau_id", nullable = false)
    private NhomMau nhomMau;

    // Yêu cầu được tạo bởi một nhân viên
    @ManyToOne
    @JoinColumn(name = "nhan_vien_id")
    private NhanVien nguoiTao;
}