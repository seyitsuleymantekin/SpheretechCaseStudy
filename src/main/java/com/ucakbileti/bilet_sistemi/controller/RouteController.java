package com.ucakbileti.bilet_sistemi.controller;

import com.ucakbileti.bilet_sistemi.dto.RouteRequest; // Paket ismini kontrol et
import com.ucakbileti.bilet_sistemi.entity.Airport;
import com.ucakbileti.bilet_sistemi.entity.Route;
import com.ucakbileti.bilet_sistemi.repository.AirportRepository;
import com.ucakbileti.bilet_sistemi.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
public class RouteController {

    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private AirportRepository airportRepository;

    @GetMapping("/search/name")
    public List<Route> searchRouteByName(@RequestParam String dep, @RequestParam String arr) {
        return routeRepository.findByDepartureAirport_NameAndArrivalAirport_Name(dep, arr);
    }

    @GetMapping("/search/code")
    public List<Route> searchRouteByCode(@RequestParam String dep, @RequestParam String arr) {
        return routeRepository.findByDepartureAirport_CodeAndArrivalAirport_Code(dep, arr);
    }

    @PostMapping
    public Route createRoute(@RequestBody RouteRequest request) {
        Airport dep = airportRepository.findByCode(request.getDepartureAirportCode());
        Airport arr = airportRepository.findByCode(request.getArrivalAirportCode());
        Route route = new Route();
        route.setDepartureAirport(dep);
        route.setArrivalAirport(arr);

        return routeRepository.save(route);
    }
}