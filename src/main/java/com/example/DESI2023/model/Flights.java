package com.example.DESI2023.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Flights {
    @Id
    private String flightNumber;
    private String flightType;
    private double ticketPrice;

    @Temporal(TemporalType.TIMESTAMP)
    private Date departureDateTime;

    private String flightStatus;

    @ManyToOne
    private Aircraft aircraft;

    @ManyToOne
    private Cities originCity;

    @ManyToOne
    private Cities destCity;

}
