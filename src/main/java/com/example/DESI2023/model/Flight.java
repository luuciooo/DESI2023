package com.example.DESI2023.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    @Id
    private String flightNumber;
    private String flightType;
    private Double ticketPrice;

    @Temporal(TemporalType.TIMESTAMP)
    private Date departureDateTime;

    private String flightStatus;

    @ManyToOne
    private Aircraft aircraft;

    @ManyToOne
    private City originCity;

    @ManyToOne
    private City destCity;

    @OneToMany(mappedBy = "flight")
    private List<Ticket> tickets;

    public int getAvailableSeats() {
        // Obtener la cantidad total de asientos del avión (puedes ajustar esto según tu lógica)
        int totalSeats = aircraft.getNumberOfRows() * aircraft.getSeatsPerRow();

        // Obtener la cantidad de tickets vendidos para este vuelo
        int soldSeats = tickets.size();

        // Calcular y devolver la cantidad de asientos disponibles
        return totalSeats - soldSeats;
    }
}
