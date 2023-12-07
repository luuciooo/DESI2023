package com.example.DESI2023.repository;

import com.example.DESI2023.model.Flights;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightsRepository extends JpaRepository<Flights, String> {
}
