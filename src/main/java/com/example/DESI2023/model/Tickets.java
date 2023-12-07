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
public class Tickets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTicket;
    private Integer seatNumber;
    private Double amountToPay;

    @Temporal(TemporalType.TIMESTAMP)
    private Date issueDateTime;

    @ManyToOne
    private Customers customer;

    @ManyToOne
    private Flights flight;
}
