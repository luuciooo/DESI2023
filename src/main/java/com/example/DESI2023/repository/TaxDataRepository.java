package com.example.DESI2023.repository;

import com.example.DESI2023.model.TaxData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxDataRepository extends JpaRepository<TaxData, Long> {
}