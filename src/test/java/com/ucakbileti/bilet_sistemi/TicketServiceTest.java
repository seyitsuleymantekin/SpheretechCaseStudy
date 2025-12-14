package com.ucakbileti.bilet_sistemi;

import com.ucakbileti.bilet_sistemi.entity.Flight;
import com.ucakbileti.bilet_sistemi.entity.Ticket;
import com.ucakbileti.bilet_sistemi.repository.FlightRepository;
import com.ucakbileti.bilet_sistemi.repository.TicketRepository;
import com.ucakbileti.bilet_sistemi.service.TicketService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @InjectMocks
    private TicketService ticketService;
    @Mock
    private TicketRepository ticketRepository;
    @Mock
    private FlightRepository flightRepository;

    @Test
    void shouldBuyTicket_StandardPrice() {
        Flight flight = new Flight();
        flight.setId(1L);
        flight.setBasePrice(100.0);
        flight.setSeat(100);
        flight.setSoldSeats(0);

        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));
        when(ticketRepository.save(any(Ticket.class))).thenAnswer(i -> i.getArguments()[0]);

        Ticket result = ticketService.buyTicket(1L, "1234567812345678");

        assertEquals(100.0, result.getPricePaid(), "Price should be base price...");
        assertEquals("123456******5678", result.getCreditCardMasked(), "Card number should be masked..");
    }

    @Test
    void shouldBuyTicket_IncreasedPrice() {
        Flight flight = new Flight();
        flight.setId(1L);
        flight.setBasePrice(100.0);
        flight.setSeat(10);
        flight.setSoldSeats(1);

        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));
        when(ticketRepository.save(any(Ticket.class))).thenAnswer(i -> i.getArguments()[0]);

        Ticket result = ticketService.buyTicket(1L, "1234567812345678");

        assertEquals(110.0, result.getPricePaid(), "Price should increase by %10..");
    }

    @Test
    void shouldMaskCreditCard_WithSeparators() {
        Flight flight = new Flight();
        flight.setSeat(100);
        flight.setSoldSeats(0);
        flight.setBasePrice(100);

        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));
        when(ticketRepository.save(any(Ticket.class))).thenAnswer(i -> i.getArguments()[0]);

        Ticket result = ticketService.buyTicket(1L, "1234-5678 9012-3456");

        assertEquals("123456******3456", result.getCreditCardMasked());
    }

    @Test
    void shouldThrowException_WhenFlightNotFound() {
        when(flightRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            ticketService.buyTicket(99L, "1234567890123456");
        });
    }
}