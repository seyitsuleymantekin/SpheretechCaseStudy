package com.ucakbileti.bilet_sistemi.controller;

import com.ucakbileti.bilet_sistemi.dto.AirlineRequest;
import com.ucakbileti.bilet_sistemi.entity.Airline;
import com.ucakbileti.bilet_sistemi.repository.AirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/airlines")
public class AirlineController {

    @Autowired
    private AirlineRepository airlineRepository;

    @GetMapping("/all")
    public List<Airline> getAllAirlines() {
        return airlineRepository.findAll();
    }

    @GetMapping("/search")
    public Airline getAirlinesByName(@RequestParam String airlineName) {
        return airlineRepository.findByName(airlineName);
    }

    @PostMapping
    public Airline createAirline(@RequestBody AirlineRequest request) {
        Airline airline = new Airline();
        airline.setName(request.getName());
        return airlineRepository.save(airline);
    }
}