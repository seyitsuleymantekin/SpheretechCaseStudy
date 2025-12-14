package com.ucakbileti.bilet_sistemi.service;

import com.ucakbileti.bilet_sistemi.entity.Flight;
import com.ucakbileti.bilet_sistemi.entity.Ticket;
import com.ucakbileti.bilet_sistemi.repository.FlightRepository;
import com.ucakbileti.bilet_sistemi.repository.TicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private FlightRepository flightRepository;

    @Transactional
    public Ticket buyTicket(Long flightId, String rawCreditCardNumber) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new RuntimeException("No Flight Founded..."));

        if (flight.getSoldSeats() >= flight.getSeat()) {
            throw new RuntimeException("No more seat is available...");
        }

        double currentPrice = calculateDynamicPrice(flight);
        String maskedCard = maskCreditCard(rawCreditCardNumber);

        // Create Ticker
        Ticket ticket = new Ticket();
        ticket.setFlight(flight);
        ticket.setPricePaid(currentPrice);
        ticket.setCreditCardMasked(maskedCard);
        ticket.setTicketNumber(UUID.randomUUID().toString());
        ticket.setCanceled(false);

        flight.setSoldSeats(flight.getSoldSeats() + 1);
        flightRepository.save(flight);

        return ticketRepository.save(ticket);
    }

    public Ticket cancelTicket(String ticketNumber) {
        Ticket ticket = ticketRepository.findByTicketNumber(ticketNumber);

        ticket.setCanceled(true);

        Flight flight = ticket.getFlight();
        flight.setSoldSeats(flight.getSoldSeats() - 1);
        flightRepository.save(flight);

        return ticketRepository.save(ticket);
    }

    private double calculateDynamicPrice(Flight flight) {
        double basePrice = flight.getBasePrice();
        int quota = flight.getSeat();
        int sold = flight.getSoldSeats();

        double occupancyRate = (double) sold / quota;

        int increaseMultiplier = (int) (occupancyRate * 10);

        double newPrice = basePrice + (basePrice * (increaseMultiplier * 0.10));
        return newPrice;
    }

    private String maskCreditCard(String rawCardNumber) {
        String cleanNumber = rawCardNumber.replaceAll("[^0-9]", "");

        if (cleanNumber.length() < 10) {
            throw new RuntimeException("Invalid credit card number...");
        }

        String beginning = cleanNumber.substring(0, 6);
        String ending = cleanNumber.substring(cleanNumber.length() - 4);

        return beginning + "******" + ending;
    }

    public Ticket getTicketByNumber(String ticketNumber) {
        return ticketRepository.findByTicketNumber(ticketNumber);
    }
}