package com.ucakbileti.bilet_sistemi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ucakbileti.bilet_sistemi.controller.RouteController;
import com.ucakbileti.bilet_sistemi.dto.RouteRequest;
import com.ucakbileti.bilet_sistemi.entity.Airport;
import com.ucakbileti.bilet_sistemi.entity.Route;
import com.ucakbileti.bilet_sistemi.repository.AirportRepository;
import com.ucakbileti.bilet_sistemi.repository.RouteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RouteController.class)
class RouteControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockitoBean
    private RouteRepository routeRepository;
    @MockitoBean
    private AirportRepository airportRepository;

    @Test
    void shouldCreateRoute() throws Exception {
        RouteRequest request = new RouteRequest();
        request.setDepartureAirportCode("IST");
        request.setArrivalAirportCode("ESB");

        when(airportRepository.findByCode("IST")).thenReturn(new Airport());
        when(airportRepository.findByCode("ESB")).thenReturn(new Airport());

        when(routeRepository.save(any(Route.class))).thenReturn(new Route());
        mockMvc.perform(post("/api/routes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }
}