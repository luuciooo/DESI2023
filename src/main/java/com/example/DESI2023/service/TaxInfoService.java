package com.example.DESI2023.service;

import com.example.DESI2023.model.TaxInfo;
import com.example.DESI2023.repository.TaxInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaxInfoService {

    private final TaxInfoRepository taxInfoRepository;

    @Autowired
    public TaxInfoService(TaxInfoRepository taxInfoRepository) {
        this.taxInfoRepository = taxInfoRepository;
    }

    public TaxInfo getTaxInfo() {
        return taxInfoRepository.findById(1L).orElse(new TaxInfo());
    }

    public void saveTaxInfo(TaxInfo taxInfo) {
        taxInfo.setId(1L); // Siempre trabajamos con el ID 1 para editar el mismo registro
        taxInfoRepository.save(taxInfo);
    }
}