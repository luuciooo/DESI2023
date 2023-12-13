package com.example.DESI2023.service;

import com.example.DESI2023.model.City;
import com.example.DESI2023.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository repository;


    public List<City> allCity(){
        return repository.findAll();
    }
}
