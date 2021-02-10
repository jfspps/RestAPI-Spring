package com.example.restapidemo.services;

import com.example.restapidemo.api.mapper.CustomerMapper;
import com.example.restapidemo.api.model.CustomerAPI;
import com.example.restapidemo.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerAPI> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customerMapper::customerToCustomerAPI)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerAPI getCustomerByLastName(String lastname) {
        return customerMapper.customerToCustomerAPI(customerRepository.findFirstByLastname(lastname));
    }
}
