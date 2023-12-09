package com.example.DESI2023.repository;

import com.example.DESI2023.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {
    //Metodo para buscar vuelos por numero.
    Flight findByFlightNumber(String flightNumber);
}
