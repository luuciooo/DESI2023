package com.example.DESI2023.service;

import com.example.DESI2023.model.TaxData;
import com.example.DESI2023.repository.TaxDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaxDataService {
    private final TaxDataRepository taxDataRepository;

    @Autowired
    public TaxDataService(TaxDataRepository taxDataRepository) {
        this.taxDataRepository = taxDataRepository;
    }


    public TaxData getTaxData() {
        Optional<TaxData> optionalTaxData = taxDataRepository.findById(1L);
        return optionalTaxData.orElseGet(TaxData::new);
    }

    public TaxData saveTaxData(TaxData taxData) {
        taxData.setId(1L);
        return taxDataRepository.save(taxData);
    }

}