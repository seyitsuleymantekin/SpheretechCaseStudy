package com.ucakbileti.bilet_sistemi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ticketNumber;
    @ManyToOne
    private Flight flight;
    private String creditCardMasked;
    private double pricePaid;
    private boolean isCanceled = false;
}