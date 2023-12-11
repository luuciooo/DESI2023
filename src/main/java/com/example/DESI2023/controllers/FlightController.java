package com.example.DESI2023.controllers;

import com.example.DESI2023.model.Flight;
import com.example.DESI2023.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/flights")
public class FlightController {

@Autowired
private FlightService service;

    @GetMapping("/programar")
    public String mostrarFormularioProgramarVuelo(Model model) {
        // Inicializar un objeto Vuelo para el formulario
        model.addAttribute("flight", new Flight());
        return "programarVuelo";
    }

    @GetMapping("/all")
    public String mostrarTodosLosVuelos(Model model) {
        List<Flight> flights = service.allFligth();
        model.addAttribute("vuelos", flights);
        return "mostrarVuelos";
    }
}
