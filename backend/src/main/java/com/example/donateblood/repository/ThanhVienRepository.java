package com.example.donateblood.repository;

import com.example.donateblood.entity.ThanhVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThanhVienRepository extends JpaRepository<ThanhVien, Integer> {
    // Để trống
}