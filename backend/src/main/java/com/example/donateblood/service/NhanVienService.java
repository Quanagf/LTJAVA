package com.example.donateblood.service;

import com.example.donateblood.repository.YeuCauMauRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NhanVienService {
    @Autowired
    private YeuCauMauRepository yeuCauMauRepository;

    public void quanLyYeuCauMau() {
        // Logic để quản lý các yêu cầu máu
        System.out.println("LOGIC: Nhân viên đang quản lý yêu cầu máu.");
    }

    public void quanLyKhoMau() {
        // Logic để quản lý kho máu (thêm, xóa đơn vị máu)
        System.out.println("LOGIC: Nhân viên đang quản lý kho máu.");
    }

    public void xacNhanHienMau(Integer donationId) {
        // Logic để xác nhận một lượt hiến máu
        System.out.println("LOGIC: Nhân viên xác nhận lượt hiến máu " + donationId);
    }
}