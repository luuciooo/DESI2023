package com.example.DESI2023.controllers;

import com.example.DESI2023.model.Flight;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/flights")
public class FlightController {

    @GetMapping("/programar")
    public String mostrarFormularioProgramarVuelo(Model model) {
        // Inicializar un objeto Vuelo para el formulario
        model.addAttribute("vuelo", new Flight());
        return "programarVuelo";
    }
}
