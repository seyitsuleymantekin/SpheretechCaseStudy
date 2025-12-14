package com.ucakbileti.bilet_sistemi.controller;

import com.ucakbileti.bilet_sistemi.dto.FlightRequest;
import com.ucakbileti.bilet_sistemi.entity.Airline;
import com.ucakbileti.bilet_sistemi.entity.Flight;
import com.ucakbileti.bilet_sistemi.entity.Route;
import com.ucakbileti.bilet_sistemi.repository.AirlineRepository;
import com.ucakbileti.bilet_sistemi.repository.FlightRepository;
import com.ucakbileti.bilet_sistemi.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private AirlineRepository airlineRepository;
    @Autowired
    private RouteRepository routeRepository;

    @GetMapping("/search/airline-name")
    public List<Flight> getFlightsByAirlineName(@RequestParam String airlineName) {
        return flightRepository.findByAirline_Name(airlineName);
    }

    @GetMapping("/search/departure")
    public List<Flight> getFlightsByDeparture(@RequestParam String airportName) {
        return flightRepository.findByRoute_DepartureAirport_Name(airportName);
    }

    @GetMapping("/search/arrival")
    public List<Flight> getFlightsByArrival(@RequestParam String airportName) {
        return flightRepository.findByRoute_ArrivalAirport_Name(airportName);
    }

    @PostMapping
    public Flight createFlight(@RequestBody FlightRequest request) {
        Airline airline = airlineRepository.findByName(request.getAirlineName());
        Route route = routeRepository.findById(request.getRouteId())
                .orElseThrow(() -> new RuntimeException("Route not found..."));

        Flight flight = new Flight();
        flight.setAirline(airline);
        flight.setRoute(route);
        flight.setBasePrice(request.getBasePrice());
        flight.setSeat(request.getSeat());
        flight.setSoldSeats(0);

        return flightRepository.save(flight);
    }
}