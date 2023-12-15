package com.example.DESI2023.repository;

import com.example.DESI2023.model.Aircraft;
import com.example.DESI2023.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Date;

@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {
    Flight findByFlightNumber(String flightNumber);

    List<Flight> findByDepartureDateTimeBetween(Date startDate, Date endDate);


    List<Flight> findByDepartureDateTimeBetweenAndOriginCityIdCityAndDestCityIdCity(
            Date startDate, Date endDate, Long originCityId, Long destCityId);

    List<Flight> findByOriginCityIdCity(Long originCityId);

    List<Flight> findByDestCityIdCity(Long destCityId);

    List<Flight> findByDepartureDateTimeBetweenAndDestCityIdCity(Date startDate, Date endDate, Long destCityId);

    List<Flight>  findByDepartureDateTimeBetweenAndOriginCityIdCity(Date startDate, Date endDate, Long originCityId);

    List<Flight>  findByOriginCityIdCityAndDestCityIdCity(Long originCityId, Long destCityId);


    @Query("SELECT DISTINCT DATE(f.departureDateTime) FROM Flight f WHERE f.aircraft = :aircraft")
    List<Date> findFlightDatesByAircraft(@Param("aircraft") Aircraft aircraft);

    boolean existsByFlightNumber(String flightNumber);
}
