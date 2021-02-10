package com.example.restapidemo.services;

import com.example.restapidemo.api.mapper.CustomerMapper;
import com.example.restapidemo.api.model.CustomerAPI;
import com.example.restapidemo.domain.Customer;
import com.example.restapidemo.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public static final String CUSTOMER_URL = "/api/customers";

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

    @Override
    public CustomerAPI getCustomerById(Long id) {
        return customerMapper.customerToCustomerAPI(customerRepository.findCustomerById(id));
    }

    @Override
    public List<CustomerAPI> getAllCustomersByLastName(String lastName) {
        return customerRepository.findAllByLastname(lastName).stream()
                .map(customerMapper::customerToCustomerAPI)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerAPI createCustomer(CustomerAPI customerAPI) {

        Customer newCustomer = customerMapper.customerAPIToCustomer(customerAPI);
        Customer saved = customerRepository.save(newCustomer);

        CustomerAPI foundCustomerAPI = customerMapper.customerToCustomerAPI(saved);
        foundCustomerAPI.setCustomer_url(CUSTOMER_URL + "/id/" + saved.getId());

        log.info("Customer added: " + foundCustomerAPI.getCustomer_url());
        return foundCustomerAPI;
    }
}
