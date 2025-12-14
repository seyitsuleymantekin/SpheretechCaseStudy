package com.ucakbileti.bilet_sistemi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Airline airline;
    @ManyToOne
    private Route route;
    private double basePrice;
    private int seat;
    private int soldSeats = 0;
}