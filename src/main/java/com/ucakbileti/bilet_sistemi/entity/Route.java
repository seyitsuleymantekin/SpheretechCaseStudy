package com.ucakbileti.bilet_sistemi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Airport departureAirport;
    @ManyToOne
    private Airport arrivalAirport;
}