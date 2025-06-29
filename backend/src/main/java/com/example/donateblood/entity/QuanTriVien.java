package com.example.donateblood.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "quan_tri_vien")
public class QuanTriVien extends NhanVien {
    // Có thể thêm các thuộc tính riêng cho quản trị viên
}