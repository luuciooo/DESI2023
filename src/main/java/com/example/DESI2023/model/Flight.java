package com.example.DESI2023.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Getter
@Setter
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

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
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

        int totalSeats = aircraft.getNumberOfRows() * aircraft.getSeatsPerRow();


        int soldSeats = tickets.size();

        return totalSeats - soldSeats;
    }
    
    
}
