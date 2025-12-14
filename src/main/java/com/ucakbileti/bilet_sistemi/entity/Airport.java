package com.ucakbileti.bilet_sistemi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code; // IST, ESB
}