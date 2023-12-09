package com.example.DESI2023.service;

import com.example.DESI2023.model.Flight;
import com.example.DESI2023.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {
    private final FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository){
        this.flightRepository = flightRepository;
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightsById(String flightNumber){
        return flightRepository.findByFlightNumber(flightNumber);
    }
    /*
    public List<Seat> getAvailableSeatsForFlight(Long flightNumber){
        //Logica para los asientos. No estoy seguro que sea esta la forma.
    }
    Flight flight =getFlightsById(flightNumber);
    if (flight != null) {
        // Suponiendo que Seat tenga un atributo booleano 'available' para determinar si está disponible
        return flight.getSeats().stream()
                .filter(Seat::isAvailable)
                .collect(Collectors.toList());
    } else {
        return null;
    }
    */
}
