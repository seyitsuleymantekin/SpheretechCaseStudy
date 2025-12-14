package com.ucakbileti.bilet_sistemi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Airline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}