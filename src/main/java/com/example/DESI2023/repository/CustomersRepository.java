package com.example.DESI2023.repository;

import com.example.DESI2023.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomersRepository extends JpaRepository<Customers, Long> {


    //Método para buscar un cliente por su número de DNI.
    Customers findByDni(long dni);

}
