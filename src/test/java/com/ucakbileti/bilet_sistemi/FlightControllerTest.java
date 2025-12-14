package com.ucakbileti.bilet_sistemi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ucakbileti.bilet_sistemi.controller.FlightController;
import com.ucakbileti.bilet_sistemi.dto.FlightRequest;
import com.ucakbileti.bilet_sistemi.entity.Airline;
import com.ucakbileti.bilet_sistemi.entity.Flight;
import com.ucakbileti.bilet_sistemi.entity.Route;
import com.ucakbileti.bilet_sistemi.repository.AirlineRepository;
import com.ucakbileti.bilet_sistemi.repository.FlightRepository;
import com.ucakbileti.bilet_sistemi.repository.RouteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FlightController.class)
class FlightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private FlightRepository flightRepository;
    @MockitoBean
    private AirlineRepository airlineRepository;
    @MockitoBean
    private RouteRepository routeRepository;

    @Test
    void shouldCreateFlight() throws Exception {
        FlightRequest request = new FlightRequest();
        request.setAirlineName("Turk Hava Yollari");
        request.setRouteId(1L);
        request.setBasePrice(1000.0);
        request.setSeat(100);

        when(airlineRepository.findById(1L)).thenReturn(Optional.of(new Airline()));
        when(routeRepository.findById(1L)).thenReturn(Optional.of(new Route()));
        when(flightRepository.save(any(Flight.class))).thenReturn(new Flight());

        mockMvc.perform(post("/api/flights")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))

                .andExpect(status().isOk());
    }
}