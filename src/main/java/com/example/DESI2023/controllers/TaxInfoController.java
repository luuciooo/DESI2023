package com.example.DESI2023.controllers;

import com.example.DESI2023.model.TaxInfo;
import com.example.DESI2023.repository.TaxInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TaxInfoController {

    private final TaxInfoRepository taxInfoRepository;

    @Autowired
    public TaxInfoController(TaxInfoRepository taxInfoRepository) {
        this.taxInfoRepository = taxInfoRepository;
    }

    @GetMapping("/tax")
    public String showTaxInfoForm(Model model) {
        TaxInfo taxInfo = taxInfoRepository.findById(1L).orElse(new TaxInfo());
        model.addAttribute("taxInfo", taxInfo);
        return "editorDatosImpositivosTasas";
    }

    @PostMapping("/tax")
    public String saveTaxInfo(TaxInfo taxInfo) {
        taxInfo.setId(1L); // Siempre trabajamos con el ID 1 para editar el mismo registro
        taxInfoRepository.save(taxInfo);
        return "redirect:/edit"; // Redirige de vuelta al formulario de edición
    }
}
