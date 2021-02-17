package com.example.repositories;

import com.example.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findFirstByLastname(String lastName);

    List<Customer> findAllByLastname(String lastName);

    Customer findCustomerById(Long id);
}
