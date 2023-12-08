package com.example.DESI2023.controllers;

import com.example.DESI2023.model.Customers;
import com.example.DESI2023.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customers")
public class CustomersController {
    private final CustomersService customersService;
    //private final FlightsService flightsService;

    @Autowired
    public CustomersController(CustomersService customersService, FligthsService fligthsService){
        this.customersService=customersService;
        this.flightsService=fligthsService;
    }
    @GetMapping("/search/{dni}")
    public String searchCustomersByDni(@PathVariable Long dni, Model model){
        Customers customers=customersService.findByDni(dni);
        if (customers!=null){
            //Mostrar los datos del cliente.
            model.addAttribute("customer", customers);
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
