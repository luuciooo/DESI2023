package com.example.DESI2023.repository;

import com.example.DESI2023.model.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketsRepository extends JpaRepository<Tickets, Long> {
}
