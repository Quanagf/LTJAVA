package com.example.donateblood.service;

import com.example.donateblood.repository.ThanhVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class ThanhVienService {

    @Autowired
    private ThanhVienRepository thanhVienRepository;

    public void dangKySanSangHienMau(Integer thanhVienId, LocalDate thoiDiem) {
        // Logic để cập nhật trạng thái sẵn sàng hiến máu
        System.out.println("LOGIC: Thành viên " + thanhVienId + " sẵn sàng hiến máu vào " + thoiDiem);
    }

    public void xemLichSuHienMau(Integer thanhVienId) {
        // Logic để lấy lịch sử hiến máu
        System.out.println("LOGIC: Xem lịch sử hiến máu của thành viên " + thanhVienId);
    }
}