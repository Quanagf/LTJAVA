package com.example.donateblood.repository;

import com.example.donateblood.entity.YeuCauMau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YeuCauMauRepository extends JpaRepository<YeuCauMau, Integer> {
    // Để trống
}