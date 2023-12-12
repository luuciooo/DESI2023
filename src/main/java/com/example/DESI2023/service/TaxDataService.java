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

    public Optional<TaxData> getTaxDataById(Long id) {
        return taxDataRepository.findById(id);
    }

    public TaxData saveTaxData(TaxData taxData) {
        return taxDataRepository.save(taxData);
    }

}
//a ver si anda esta mierda