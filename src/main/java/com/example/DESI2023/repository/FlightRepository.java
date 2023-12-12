package com.example.DESI2023.repository;

import com.example.DESI2023.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Date;

@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {
    //Metodo para buscar vuelos por numero.
    Flight findByFlightNumber(String flightNumber);

    // Método para buscar vuelos en un rango de fechas.
    List<Flight> findByDepartureDateTimeBetween(Date startDate, Date endDate);
}
