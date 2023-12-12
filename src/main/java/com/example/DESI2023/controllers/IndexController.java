package com.example.DESI2023.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "Index"; // Esto hace referencia al nombre del archivo HTML (index.html) en tu carpeta de plantillas Thymeleaf
    }

    @GetMapping("/programarVuelo")
    public String programarVuelo() {
        return "programarVuelo"; // Nombre del archivo HTML para la página de programar vuelo
    }

    @GetMapping("/mostrarVuelos")
    public String mostrarVuelos() {
        return "mostrarVuelos"; // Nombre del archivo HTML para la página de mostrar vuelos
    }

    @GetMapping("/emisionTickets")
    public String emisionTickets() {
        return "emisionTickets"; // Nombre del archivo HTML para la página de emisión de tickets
    }
}

