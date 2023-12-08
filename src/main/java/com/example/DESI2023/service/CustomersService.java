package com.example.DESI2023.service;

import com.example.DESI2023.model.Customers;
import com.example.DESI2023.repository.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomersService {
    private final CustomersRepository customersRepository;

    @Autowired
    public CustomersService(CustomersRepository customersRepository){
        this.customersRepository=customersRepository;
    }

    public Customers findByDni(Long dni){
        return customersRepository.findByDni(dni);
    }
}
