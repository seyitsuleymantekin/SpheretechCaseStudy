package com.ucakbileti.bilet_sistemi.controller;

import com.ucakbileti.bilet_sistemi.dto.TicketRequest;
import com.ucakbileti.bilet_sistemi.entity.Ticket;
import com.ucakbileti.bilet_sistemi.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/buy")
    public Ticket buyTicket(@RequestBody TicketRequest request) {
        return ticketService.buyTicket(request.getFlightId(), request.getCreditCard());
    }

    @GetMapping("/{ticketNumber}")
    public Ticket getTicketByNumber(@PathVariable String ticketNumber) {
        return ticketService.getTicketByNumber(ticketNumber);
    }

    @DeleteMapping("/{ticketNumber}")
    public Ticket cancelTicket(@PathVariable String ticketNumber) {
        return ticketService.cancelTicket(ticketNumber);
    }
}