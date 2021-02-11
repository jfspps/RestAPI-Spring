package com.example.restapidemo.services;

import com.example.restapidemo.api.model.CustomerAPI;

import java.util.List;

public interface CustomerService {
    List<CustomerAPI> getAllCustomers();

    CustomerAPI getCustomerByLastName(String lastname);

    CustomerAPI getCustomerById(Long id);

    List<CustomerAPI> getAllCustomersByLastName(String lastName);

    CustomerAPI createCustomer(CustomerAPI customerAPI);

    CustomerAPI saveCustomer(Long id, CustomerAPI customerAPI);

    // similar to saveCustomer except that some fields are allowed to be null (e.g. update first name only)
    CustomerAPI patchCustomer(Long id, CustomerAPI customerDTO);
}
