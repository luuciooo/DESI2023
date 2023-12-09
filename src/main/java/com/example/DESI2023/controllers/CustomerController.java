package com.example.DESI2023.controllers;

import com.example.DESI2023.model.Customer;
import com.example.DESI2023.service.CustomerService;
import com.example.DESI2023.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
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
    public String searchCustomersByDni(@PathVariable Long dni, Model model) {
        Customer customer = customerService.findByDni(dni);
        if (customer != null) {
            //Mostrar los datos del cliente.
            model.addAttribute("customer", customer);
            //Mostrar todos los vuelos disponibles.
            model.addAttribute("flights", flightService.getAllFlights());
            //Nombre de la vista donde se muestran los datos del cliente y los vuelos.
            return "customersDetails";
        } else {
            //Manejar el caso de que el cliente no se encuentre registrado.
            return "customerNotFound";
        }
    }
}
