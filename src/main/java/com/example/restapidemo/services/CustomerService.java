package com.example.restapidemo.services;

import com.example.restapidemo.api.model.CustomerAPI;

import java.util.List;

public interface CustomerService {
    List<CustomerAPI> getAllCustomers();

    CustomerAPI getCustomerByLastName(String lastname);

    CustomerAPI getCustomerById(Long id);

    List<CustomerAPI> getAllCustomersByLastName(String lastName);

    CustomerAPI createCustomer(CustomerAPI customerAPI);
}
