package com.ucakbileti.bilet_sistemi.dto;

import lombok.Data;

@Data
public class FlightRequest {
    private String airlineName;
    private Long routeId;
    private double basePrice;
    private int seat;

}