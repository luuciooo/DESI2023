package com.example.DESI2023.controllers;

import com.example.DESI2023.model.Aircraft;
import com.example.DESI2023.model.City;
import com.example.DESI2023.model.Flight;
import com.example.DESI2023.service.AircraftService;
import com.example.DESI2023.service.CityService;
import com.example.DESI2023.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService service;
    @Autowired
    private CityService cityService;
    @Autowired
    private AircraftService aircraftService;

    @GetMapping("/form")
    public String mostrarFormularioProgramarVuelo(Model model,@ModelAttribute("operacionExitosa") String operacionExitosa) {

        List<City> listOfCities = cityService.allCity();
        List <Aircraft> listOfAircrafts = aircraftService.allAircraft();

        model.addAttribute("flight", new Flight());
        model.addAttribute("listOfCities", listOfCities);
        model.addAttribute("listOfAircrafts", listOfAircrafts);
        return "programarVuelo";
    }

    @PostMapping("/programar")
    public String programarVuelo(@ModelAttribute("flight") @Valid Flight flight, BindingResult result, Model model) {

        if (result.hasErrors()) {
            // Si hay errores de validación, agregarlos al modelo
            model.addAttribute("listOfCities", cityService.allCity());
            model.addAttribute("listOfAircrafts", aircraftService.allAircraft());

            // Agregar los errores de validación al modelo
            model.addAttribute("validationErrors", result.getAllErrors());
            
            

            return "programarVuelo";
        }

        // Realizar otras validaciones específicas
        if (service.existeVueloEnMismoDia(flight) 
        		|| service.existeNumeroDeVuelo(flight.getFlightNumber())) {
            result.rejectValue("departureDateTime", "error.flight", "Ya hay un vuelo programado para este avión en el mismo día o ya existe el numero de vuelo.");
            model.addAttribute("listOfCities", cityService.allCity());
            model.addAttribute("listOfAircrafts", aircraftService.allAircraft());
            return "programarVuelo";
        }


        flight.setFlightStatus("Normal");


        service.programarVuelo(flight);
        
        

        return "redirect:/flights/form?operacionExitosa='El vuelo se a creado con exito!!";
    }

    @GetMapping("/show")
    public String mostrarTodosLosVuelos(
            @RequestParam(name = "fecha", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
            @RequestParam(name = "origen", required = false) Long idCiudadOrigen,
            @RequestParam(name = "destino", required = false) Long idCiudadDestino,
            Model model) {

        List<Flight> flights;


        if (fecha != null || idCiudadOrigen != null || idCiudadDestino != null) {

            flights = service.getFlightsByFilters(fecha, idCiudadOrigen, idCiudadDestino);
        } else {

            flights = service.allFligth();
        }


        List<City> ciudades = service.getAllCities();
        model.addAttribute("vuelos", flights);
        model.addAttribute("fecha", fecha);
        model.addAttribute("ciudades", ciudades);
        model.addAttribute("origen", idCiudadOrigen);
        model.addAttribute("destino", idCiudadDestino);

        return "mostrarVuelos";
    }


}
