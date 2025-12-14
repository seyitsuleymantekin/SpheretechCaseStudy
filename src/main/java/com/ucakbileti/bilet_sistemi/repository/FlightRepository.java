package com.ucakbileti.bilet_sistemi.repository;

import com.ucakbileti.bilet_sistemi.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByAirline_Name(String airlineName);

    List<Flight> findByRoute_DepartureAirport_Name(String departureAirportName);

    List<Flight> findByRoute_ArrivalAirport_Name(String arrivalAirportName);

}