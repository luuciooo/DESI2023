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
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTicket;
    private Integer numberRow;
    private Integer seatNumber;
    private Double amountToPay;

    @Temporal(TemporalType.TIMESTAMP)
    private Date issueDateTime;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Flight flight;
}
