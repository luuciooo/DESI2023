package com.example.DESI2023.service;

import com.example.DESI2023.model.Flight;
import com.example.DESI2023.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    private final FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository){
        this.flightRepository = flightRepository;
    }

    public Flight findByFlightNumber(String flightNumber) {
        Optional<Flight> optionalFlight = flightRepository.findById(flightNumber);
        return optionalFlight.orElse(null);
    }

    public List<Flight> allFligth(){
        return flightRepository.findAll();
    }
    //Otros métodos de servicio para la lógica relacionada con los vuelos.
}
