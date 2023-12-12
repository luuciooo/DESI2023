package com.example.DESI2023.controllers;

import com.example.DESI2023.model.Flight;
import com.example.DESI2023.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
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
    public String mostrarTodosLosVuelos(@RequestParam(name = "fecha", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
                                        Model model) {
        List<Flight> flights;

        if (fecha != null) {
            // Si se proporciona la fecha, filtra los vuelos por esa fecha
            flights = service.getFlightsByDate(fecha);
        } else {
            // Si no se proporciona la fecha, obtiene todos los vuelos
            flights = service.allFligth();
        }

        model.addAttribute("vuelos", flights);
        return "mostrarVuelos";
    }


}
