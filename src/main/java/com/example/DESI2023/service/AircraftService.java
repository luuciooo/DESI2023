package com.example.DESI2023.service;


import com.example.DESI2023.model.Aircraft;
import com.example.DESI2023.repository.AircraftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AircraftService {
    @Autowired
    private AircraftRepository repository;


    public List<Aircraft> allAircraft(){
        return repository.findAll();
    }
}
