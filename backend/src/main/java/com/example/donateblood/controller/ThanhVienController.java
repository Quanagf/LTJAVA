package com.example.donateblood.controller;

import com.example.donateblood.service.ThanhVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/thanh-vien")
public class ThanhVienController {

    @Autowired
    private ThanhVienService thanhVienService;

    // Giả sử userID được lấy từ token xác thực
    private Integer getCurrentUserId() { return 1; }

    @PostMapping("/dang-ky-san-sang")
    public ResponseEntity<Void> dangKySanSangHienMau(@RequestParam LocalDate thoiDiem) {
        Integer currentUserId = getCurrentUserId();
        thanhVienService.dangKySanSangHienMau(currentUserId, thoiDiem);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/lich-su-hien-mau")
    public ResponseEntity<Void> xemLichSuHienMau() {
        Integer currentUserId = getCurrentUserId();
        thanhVienService.xemLichSuHienMau(currentUserId);
        // Trong thực tế sẽ trả về một List<LichSuHienMauDTO>
        return ResponseEntity.ok().build();
    }
}