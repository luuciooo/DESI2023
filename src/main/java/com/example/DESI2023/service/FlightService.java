package com.example.DESI2023.service;

import com.example.DESI2023.model.Flight;
import com.example.DESI2023.model.Ticket;
import com.example.DESI2023.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    private final FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public Flight findByFlightNumber(String flightNumber) {
        Optional<Flight> optionalFlight = flightRepository.findById(flightNumber);
        return optionalFlight.orElse(null);
    }

    public List<Flight> allFligth(){
        return flightRepository.findAll();
    }

    public List<Flight> getFlightsByDate(LocalDate fecha) {
        // Obtén la fecha de inicio y fin del día
        LocalDateTime startOfDay = fecha.atStartOfDay();
        LocalDateTime endOfDay = fecha.atTime(LocalTime.MAX);

        // Convierte las fechas a tipo Date para comparar con la base de datos
        Date startDate = Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());

        // Llama al repositorio para obtener los vuelos en el rango de fechas
        return flightRepository.findByDepartureDateTimeBetween(startDate, endDate);
    }

}
