package com.example.DESI2023.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Tickets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTicket;
    private int seatNumber;
    private double amountToPay;

    @Temporal(TemporalType.TIMESTAMP)
    private Date issueDateTime;

    @ManyToOne
    private Customers customer;

    @ManyToOne
    private Flights flight;
}
