package com.example.DESI2023.controllers;

import com.example.DESI2023.model.TaxData;
import com.example.DESI2023.service.TaxDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class TaxDataController {
    private final TaxDataService taxDataService;

    @Autowired
    public TaxDataController(TaxDataService taxDataService) {
        this.taxDataService = taxDataService;
    }

    @GetMapping("/edit-tax-data")
    public String showTaxDataForm(Model model) {
        Optional<TaxData> taxDataOptional = taxDataService.getTaxDataById(1L); // Suponiendo que el ID es 1
        TaxData taxData = taxDataOptional.orElseGet(TaxData::new); // Si no existe, crea uno nuevo

        model.addAttribute("taxData", taxData);
        return "impuestos";
    }

    @PostMapping("/edit-tax-data")
    public String editTaxData(TaxData taxData) {
        taxDataService.saveTaxData(taxData);
        return "redirect:/edit-tax-data"; // Redirecciona al formulario después de guardar
    }
}