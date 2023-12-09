package com.example.DESI2023.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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

}
