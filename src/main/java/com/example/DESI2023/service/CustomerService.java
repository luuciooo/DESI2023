package com.example.DESI2023.service;

import com.example.DESI2023.model.Customer;
import com.example.DESI2023.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;



    public Customer findByDni(Long dni){
        return customerRepository.findByDni(dni);
    }
}
