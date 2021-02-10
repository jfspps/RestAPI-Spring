package com.example.restapidemo.repositories;

import com.example.restapidemo.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findFirstByLastname(String lastName);
}
