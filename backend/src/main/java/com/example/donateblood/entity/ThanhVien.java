package com.example.donateblood.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "thanh_vien")
public class ThanhVien extends NguoiDung {

    private LocalDate thoiDiemSanSang;
    private LocalDate ngayHienMauCuoiCung;

    // Một thành viên có một nhóm máu
    @ManyToOne
    @JoinColumn(name = "nhom_mau_id")
    private NhomMau nhomMau;

    // Một thành viên có nhiều lịch sử hiến máu
    @OneToMany(mappedBy = "thanhVien")
    private Set<LichSuHienMau> lichSuHienMaus;
}