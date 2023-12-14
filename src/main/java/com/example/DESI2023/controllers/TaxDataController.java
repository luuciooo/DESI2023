package com.example.DESI2023.controllers;

import com.example.DESI2023.model.TaxData;
import com.example.DESI2023.service.TaxDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/taxdata")
public class TaxDataController {
    private final TaxDataService taxDataService;

    @Autowired
    public TaxDataController(TaxDataService taxDataService) {
        this.taxDataService = taxDataService;
    }

    @GetMapping("/form")
    public String showTaxDataForm(Model model) {
        model.addAttribute("taxData", taxDataService.getTaxData());
        return "impuestos";
    }

    @PostMapping("/update")
    public String updateTaxData(@ModelAttribute("taxData") TaxData taxData) {
        taxDataService.saveTaxData(taxData);
        return "redirect:/taxdata/form";
    }

}