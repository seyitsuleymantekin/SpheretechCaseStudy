package com.ucakbileti.bilet_sistemi.controller;

import com.ucakbileti.bilet_sistemi.dto.AirportRequest;
import com.ucakbileti.bilet_sistemi.entity.Airport;
import com.ucakbileti.bilet_sistemi.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/airports")
public class AirportController {

    @Autowired
    private AirportRepository airportRepository;

    @GetMapping("/all")
    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    @GetMapping("/search/name")
    public Airport getAirportsByName(@RequestParam String airportName) {
        return airportRepository.findByName(airportName);
    }

    @GetMapping("/search/code")
    public Airport getAirportsByCode(@RequestParam String airportCode) {
        return airportRepository.findByCode(airportCode);
    }

    @PostMapping
    public Airport createAirport(@RequestBody AirportRequest request) {
        Airport airport = new Airport();
        airport.setName(request.getName());
        airport.setCode(request.getCode().toUpperCase());
        return airportRepository.save(airport);
    }
}