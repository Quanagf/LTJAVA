package com.example.donateblood.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "nhom_mau")
public class NhomMau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String tenNhom; // A, B, AB, O
    private String rhFactor; // +, -
}