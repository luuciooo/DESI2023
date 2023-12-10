package com.example.DESI2023.controllers;

import com.example.DESI2023.model.Customer;
import com.example.DESI2023.model.Flight;
import com.example.DESI2023.service.CustomerService;
import com.example.DESI2023.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final FlightService flightService;

    @Autowired
    public CustomerController(CustomerService customerService, FlightService flightService) {
        this.customerService = customerService;
        this.flightService = flightService;
    }

    //Endpoint para obtener datos del cliente por DNI
    @GetMapping("/{dni}")
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

    //Endpoint para seleccionar vuelo y asiento
    @PostMapping("/{dni}/selected-flight")
    public ResponseEntity<?> selectFlightAndSeat(
            @PathVariable Long dni,
            @RequestParam String flightNumber,
            @RequestParam int seatNumber
    ) {
        Customer customer=customerService.findByDni(dni);
        Flight selectedFlight=flightService.findByFlightNumber(flightNumber);

        if (customer == null || selectedFlight == null) {
            // Cliente o vuelo no encontrados, se devuelve un mensaje de error
            String errorMessage = "Cliente o vuelo no encontrados.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }

        // Lógica para calcular el precio según el tipo de vuelo
        double ticketPrice = calculateTicketPrice(selectedFlight);

        if (selectedFlight.getFlightType().equals("INTERNACIONAL") && customer.getPassportNumber() == null) {
            // Verificar si es vuelo internacional y cliente sin número de pasaporte
            String errorMessage = "El cliente no tiene número de pasaporte, no se puede emitir el ticket para vuelos internacionales.";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }

        // Aquí iría la lógica para reservar el asiento y generar el ticket, así como enviar la confirmación al cliente por correo electrónico.
        // Esto sería la implementación real de la lógica que maneja la reserva de asientos y la emisión de tickets.

        // Simulación de emisión exitosa del ticket
        String successMessage = "¡El pasaje ha sido emitido exitosamente!";
        return ResponseEntity.ok().body(successMessage);
    }

    // Método para calcular el precio del ticket según el tipo de vuelo
    private double calculateTicketPrice(Flight flight) {
        double ticketPrice = flight.getTicketPrice();

        if (flight.getFlightType().equals("NACIONAL")) {
            return ticketPrice + 1100 + (0.21 * ticketPrice); // Cálculo para vuelos nacionales
        } else {
            return (ticketPrice + 1.40) * 375; // Cálculo para vuelos internacionales
        }
    }
}
