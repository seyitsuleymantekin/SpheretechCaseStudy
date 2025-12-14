package com.ucakbileti.bilet_sistemi.repository;

import com.ucakbileti.bilet_sistemi.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Long> {
    Airline findByName(String name);

}