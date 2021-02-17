package com.example.services;

import com.example.JAXBmodel.CustomerAPI;
import com.example.api.mapper.CustomerMapper;


import com.example.domain.Customer;
import com.example.exceptions.ResourceNotFoundException;
import com.example.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    // one could also get CustomerController's URL root to keep both Service and Controller url's in sync
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
        return saveCustomerAndReturnAPI(newCustomer);
    }

    // this saves the customer, regardless if a record with the ID already exists or not
    @Override
    public CustomerAPI saveCustomer(Long id, CustomerAPI customerAPI) {
        Customer customer = customerMapper.customerAPIToCustomer(customerAPI);
        customer.setId(id);

        return saveCustomerAndReturnAPI(customer);
    }

    // similar to saveCustomer except that some fields are allowed to be null (e.g. update first name only)
    // null fields are not saved to the DB
    // this is tested via an integration test, not via Mocks
    @Override
    public CustomerAPI patchCustomer(Long id, CustomerAPI customerDTO) {
        return customerRepository.findById(id).map(customer -> {

            if(customerDTO.getFirstname() != null){
                customer.setFirstname(customerDTO.getFirstname());
            }

            if(customerDTO.getLastname() != null){
                customer.setLastname(customerDTO.getLastname());
            }

            CustomerAPI updatedCustomer = customerMapper.customerToCustomerAPI(customerRepository.save(customer));
            updatedCustomer.setCustomerUrl(CUSTOMER_URL + "/id/" + id);

            return updatedCustomer;
        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

    private CustomerAPI saveCustomerAndReturnAPI(Customer newCustomer) {
        Customer saved = customerRepository.save(newCustomer);

        CustomerAPI foundCustomerAPI = customerMapper.customerToCustomerAPI(saved);
        foundCustomerAPI.setCustomerUrl(CUSTOMER_URL + "/id/" + saved.getId());

        log.info("Customer updated: " + foundCustomerAPI.getCustomerUrl());
        return foundCustomerAPI;
    }
}
