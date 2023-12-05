package com.example.DESI2023.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Airports {
    @Id
    private String idAirport;

    @OneToOne
    private Cities location;

}
