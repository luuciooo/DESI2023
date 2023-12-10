package com.example.DESI2023.repository;

import com.example.DESI2023.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    //Método para buscar un cliente por su número de DNI.
    Customer findByDni(long dni);

}
