package com.example.DESI2023.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @NotNull

    private String flightType;
    @NotNull
    @PositiveOrZero
    @DecimalMin(value = "0.01")
    @DecimalMax(value = "99999.99")
    private Double ticketPrice;

    @NotNull
    @FutureOrPresent
    @Temporal(TemporalType.TIMESTAMP)
    private Date departureDateTime;

    private String flightStatus;

    @ManyToOne
    @NotNull
    private Aircraft aircraft;

    @ManyToOne
    @NotNull
    private City originCity;

    @ManyToOne
    @NotNull
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
