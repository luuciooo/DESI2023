package com.example.DESI2023.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customers {
    @Id
    private Long dni;
    private String firtsName;
    private String lastName;
    private String address;
    private String email;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    private String passportNumber;
}
