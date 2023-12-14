package com.example.DESI2023.controllers;

import com.example.DESI2023.model.City;
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

    @GetMapping("/show")
    public String mostrarTodosLosVuelos(
            @RequestParam(name = "fecha", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
            @RequestParam(name = "origen", required = false) Long idCiudadOrigen,
            @RequestParam(name = "destino", required = false) Long idCiudadDestino,
            Model model) {

        List<Flight> flights;
        List<Flight> flights2;

        // Filtra los vuelos según los parámetros proporcionados
        if (fecha != null || idCiudadOrigen != null || idCiudadDestino != null) {
            // Filtra los vuelos según los parámetros proporcionados
            flights = service.getFlightsByFilters(fecha, idCiudadOrigen, idCiudadDestino);
        } else {
            // Si no se proporcionan parámetros, obtén todos los vuelos
            flights = service.allFligth();
        }

        // Agrega las ciudades disponibles al modelo
        List<City> ciudades = service.getAllCities();
        model.addAttribute("vuelos", flights);
        model.addAttribute("fecha", fecha);
        model.addAttribute("ciudades", ciudades);
        model.addAttribute("origen", idCiudadOrigen);
        model.addAttribute("destino", idCiudadDestino);

        return "mostrarVuelos";
    }


}
