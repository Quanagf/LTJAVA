package com.example.donateblood.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "nhan_vien")
public class NhanVien extends NguoiDung {
    // Có thể thêm các thuộc tính riêng cho nhân viên ở đây, ví dụ: chucVu, maNhanVien
}