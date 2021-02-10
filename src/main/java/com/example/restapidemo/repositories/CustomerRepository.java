package com.example.restapidemo.repositories;

import com.example.restapidemo.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findFirstByLastname(String lastName);

    List<Customer> findAllByLastname(String lastName);
}
