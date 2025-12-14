package com.ucakbileti.bilet_sistemi.repository;

import com.ucakbileti.bilet_sistemi.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {
    Airport findByName(String name);
    Airport findByCode(String code);

}