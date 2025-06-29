package com.example.donateblood.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "nguoi_dung")
@Inheritance(strategy = InheritanceType.JOINED) // Mỗi lớp con sẽ có một bảng riêng chứa các cột của nó
public abstract class NguoiDung {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String matKhau;

    private String hoTen;
    private String soDienThoai;
    private String diaChi;
}