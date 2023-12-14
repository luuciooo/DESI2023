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
    public String mostrarFormularioProgramarVuelo(Model model) {

        List<City> listOfCities = cityService.allCity();
        List <Aircraft> listOfAircrafts = aircraftService.allAircraft();

        model.addAttribute("flight", new Flight());
        model.addAttribute("listOfCities", listOfCities);
        model.addAttribute("listOfAircrafts", listOfAircrafts);
        return "programarVuelo";
    }

    @PostMapping("/programar")
    public String programarVuelo(@ModelAttribute("flight") @Valid Flight flight, BindingResult result, Model model) {
        // Validar los datos del formulario
        if (result.hasErrors()) {
            // Si hay errores de validación, agregarlos al modelo
            model.addAttribute("listOfCities", cityService.allCity());
            model.addAttribute("listOfAircrafts", aircraftService.allAircraft());

            // Agregar los errores de validación al modelo
            model.addAttribute("validationErrors", result.getAllErrors());

            return "programarVuelo";
        }

        // Realizar otras validaciones específicas
        if (service.existeVueloEnMismoDia(flight) || service.existeVueloEnMismoDia(flight)) {
            result.rejectValue("departureDateTime", "error.flight", "Ya hay un vuelo programado para este avión en el mismo día o ya existe el numero de vuelo.");
            model.addAttribute("listOfCities", cityService.allCity());
            model.addAttribute("listOfAircrafts", aircraftService.allAircraft());
            return "programarVuelo";
        }

        // Configurar otros datos del vuelo antes de guardarlo
        flight.setFlightStatus("Normal");

        // Guardar el vuelo en la base de datos
        service.programarVuelo(flight);

        model.addAttribute("mensajeExito", "¡El vuelo se ha programado exitosamente!");

        // Redirigir a alguna página de éxito o a donde desees después de programar el vuelo
        return "redirect:/flights/form";
    }

    @GetMapping("/show")
    public String mostrarTodosLosVuelos(
            @RequestParam(name = "fecha", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
            @RequestParam(name = "origen", required = false) Long idCiudadOrigen,
            @RequestParam(name = "destino", required = false) Long idCiudadDestino,
            Model model) {

        List<Flight> flights;

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
