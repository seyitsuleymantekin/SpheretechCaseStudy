package com.ucakbileti.bilet_sistemi.dto;

import lombok.Data;

@Data
public class TicketRequest {
    private Long flightId;
    private String creditCard;

}