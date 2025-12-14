package com.ucakbileti.bilet_sistemi.repository;

import com.ucakbileti.bilet_sistemi.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    List<Route> findByDepartureAirport_NameAndArrivalAirport_Name(String departureName, String arrivalName);
    List<Route> findByDepartureAirport_CodeAndArrivalAirport_Code(String departureCode, String arrivalCode);

}