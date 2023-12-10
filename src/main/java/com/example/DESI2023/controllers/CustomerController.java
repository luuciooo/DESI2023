package com.example.DESI2023.controllers;

import com.example.DESI2023.model.Customer;
import com.example.DESI2023.service.CustomerService;
import com.example.DESI2023.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final FlightService flightService;

    @Autowired
    public CustomerController(CustomerService customerService, FlightService flightService) {
        this.customerService = customerService;
        this.flightService = flightService;
    }

    @GetMapping("/search/{dni}")
    public ResponseEntity<?> getCustomerByDni(@PathVariable Long dni) {
        Customer customer = customerService.findByDni(dni);
        if (customer != null) {
            //Cliente encontrado, se devuelve la información básica
            return ResponseEntity.ok().body(customer);
        } else {
            //Cliente no encontrado, se devuelve un mensaje de error
            String errorMessage="Cliente con DNI "+dni+" no encontrado en la base de datos.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }
}
