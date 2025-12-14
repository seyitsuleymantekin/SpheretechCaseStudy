package com.ucakbileti.bilet_sistemi.dto;

import lombok.Data;

@Data
public class RouteRequest {
    private Long departureAirportId;
    private String departureAirportName;
    private String departureAirportCode;
    private Long arrivalAirportId;
    private String arrivalAirportName;
    private String arrivalAirportCode;

}