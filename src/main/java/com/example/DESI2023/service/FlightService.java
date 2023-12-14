package com.example.DESI2023.service;

import com.example.DESI2023.model.City;
import com.example.DESI2023.model.Flight;
import com.example.DESI2023.model.Ticket;
import com.example.DESI2023.repository.CityRepository;
import com.example.DESI2023.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;


@Service
public class FlightService {
    private final FlightRepository flightRepository;
    private final CityRepository cityRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository, CityRepository cityRepository) {
        this.flightRepository = flightRepository;
        this.cityRepository = cityRepository;
    }


    public Flight findByFlightNumber(String flightNumber) {
        Optional<Flight> optionalFlight = flightRepository.findById(flightNumber);
        return optionalFlight.orElse(null);
    }
    public void programarVuelo(Flight flight){
        flightRepository.save(flight);
    }
    public List<Flight> allFligth(){
        return flightRepository.findAll();
    }

    public List<Flight> getFlightsByFilters(LocalDate fecha, Long CiudadOrigen, Long CiudadDestino) {
        //Set para evitar duplicados y comparador para ordenarlo por fecha
        Set<Flight> flights = new TreeSet<>(Comparator.comparing(Flight::getDepartureDateTime));


        // Obtén la fecha de inicio y fin del día si se proporciona la fecha
        LocalDateTime startOfDay = (fecha != null) ? fecha.atStartOfDay() : null;
        LocalDateTime endOfDay = (fecha != null) ? fecha.atTime(LocalTime.MAX) : null;

        // Convierte las fechas a tipo Date para comparar con la base de datos
        Date startDate = (startOfDay != null) ? Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant()) : null;
        Date endDate = (endOfDay != null) ? Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant()) : null;

        // Filtra solo por fecha
        if (fecha != null && CiudadOrigen == null && CiudadDestino == null) {
            flights.addAll(flightRepository.findByDepartureDateTimeBetween(startDate, endDate));
        }

        // Filtra solo ciudad de origen
        if (CiudadOrigen != null && CiudadDestino == null && fecha == null) {
            flights.addAll(flightRepository.findByOriginCityIdCity(CiudadOrigen));
        }

        // Filtra solo por ciudad de destino
        if (CiudadDestino != null && CiudadOrigen == null && fecha == null) {
            flights.addAll(flightRepository.findByDestCityIdCity(CiudadDestino));
        }

        // Filtro por fecha y por ciudad de origen
        if (CiudadDestino == null && CiudadOrigen != null && fecha != null) {
            flights.addAll(flightRepository.findByDepartureDateTimeBetweenAndOriginCityIdCity(startDate, endDate, CiudadOrigen));
        }

        //Filtro por fecha y por ciudad de destino
        if (CiudadDestino != null && CiudadOrigen == null && fecha != null) {
            flights.addAll(flightRepository.findByDepartureDateTimeBetweenAndDestCityIdCity(startDate, endDate, CiudadOrigen));
        }

        //Filtro por ciudad de origen y ciudad de destino
        if (CiudadDestino != null && CiudadOrigen != null && fecha == null) {
            flights.addAll(flightRepository.findByOriginCityIdCityAndDestCityIdCity(CiudadOrigen, CiudadDestino));
        }

        //Filtro por fecha y ciudades
        if (CiudadDestino != null && CiudadOrigen != null && fecha != null) {
            flights.addAll(flightRepository.findByDepartureDateTimeBetweenAndOriginCityIdCityAndDestCityIdCity(startDate, endDate,CiudadOrigen, CiudadDestino));
        }

        // Si no se proporcionan filtros, obtén todos los vuelos
        if (fecha == null && CiudadOrigen == null && CiudadDestino == null) {
            flights.addAll(allFligth());
        }

        return new ArrayList<>(flights);
    }
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }



    public boolean existeVueloEnMismoDia(Flight flight) {



        // Realizar la consulta en el repositorio para verificar si hay un vuelo para el mismo día y avión
        List<Date> DatesFlight = flightRepository.findFlightDatesByAircraft(flight.getAircraft());

        return DatesFlight.contains(flight.getDepartureDateTime());
    }

    public boolean existeNumeroDeVuelo(String flightNumber) {
        // Verificar si ya existe un vuelo con el mismo número de vuelo
        return flightRepository.existsByFlightNumber(flightNumber);
    }


}
