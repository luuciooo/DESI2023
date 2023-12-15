package com.example.DESI2023.service;

import com.example.DESI2023.model.City;
import com.example.DESI2023.model.Flight;
import com.example.DESI2023.model.Ticket;
import com.example.DESI2023.repository.CityRepository;
import com.example.DESI2023.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;


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
        
        Set<Flight> flights = new TreeSet<>(Comparator.comparing(Flight::getDepartureDateTime));


        
        LocalDateTime startOfDay = (fecha != null) ? fecha.atStartOfDay() : null;
        LocalDateTime endOfDay = (fecha != null) ? fecha.atTime(LocalTime.MAX) : null;

        
        Date startDate = (startOfDay != null) ? Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant()) : null;
        Date endDate = (endOfDay != null) ? Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant()) : null;

        
        if (fecha != null && CiudadOrigen == null && CiudadDestino == null) {
            flights.addAll(flightRepository.findByDepartureDateTimeBetween(startDate, endDate));
        }

        
        if (CiudadOrigen != null && CiudadDestino == null && fecha == null) {
            flights.addAll(flightRepository.findByOriginCityIdCity(CiudadOrigen));
        }

        
        if (CiudadDestino != null && CiudadOrigen == null && fecha == null) {
            flights.addAll(flightRepository.findByDestCityIdCity(CiudadDestino));
        }

        
        if (CiudadDestino == null && CiudadOrigen != null && fecha != null) {
            flights.addAll(flightRepository.findByDepartureDateTimeBetweenAndOriginCityIdCity(startDate, endDate, CiudadOrigen));
        }

        
        if (CiudadDestino != null && CiudadOrigen == null && fecha != null) {
            flights.addAll(flightRepository.findByDepartureDateTimeBetweenAndDestCityIdCity(startDate, endDate, CiudadOrigen));
        }

        
        if (CiudadDestino != null && CiudadOrigen != null && fecha == null) {
            flights.addAll(flightRepository.findByOriginCityIdCityAndDestCityIdCity(CiudadOrigen, CiudadDestino));
        }

        
        if (CiudadDestino != null && CiudadOrigen != null && fecha != null) {
            flights.addAll(flightRepository.findByDepartureDateTimeBetweenAndOriginCityIdCityAndDestCityIdCity(startDate, endDate,CiudadOrigen, CiudadDestino));
        }

        
        if (fecha == null && CiudadOrigen == null && CiudadDestino == null) {
            flights.addAll(allFligth());
        }

        return new ArrayList<>(flights);
    }
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }



    public boolean existeVueloEnMismoDia(Flight flight) {
    	List<Date> datesFlight = flightRepository.findFlightDatesByAircraft(flight.getAircraft());
    	
    	List<String> lista = datesToStrings(datesFlight);
    	
    	String fecha = dateToString(flight.getDepartureDateTime());
    	
    	return lista.contains(fecha);
        
    }

    public boolean existeNumeroDeVuelo(String flightNumber) {
        
        return flightRepository.existsByFlightNumber(flightNumber);
    }
    
    public static String dateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    public static List<String> datesToStrings(List<Date> dates) {
        return dates.stream()
                .map(FlightService::dateToString)
                .collect(Collectors.toList());
    }


}
