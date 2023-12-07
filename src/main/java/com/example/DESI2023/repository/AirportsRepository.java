package com.example.DESI2023.repository;

import com.example.DESI2023.model.Airports;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportsRepository extends JpaRepository<Airports, String> {
}
