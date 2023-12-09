package com.example.DESI2023.repository;

import com.example.DESI2023.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, String> {
}
