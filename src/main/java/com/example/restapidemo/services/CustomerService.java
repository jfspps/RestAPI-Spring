package com.example.restapidemo.services;

import com.example.restapidemo.api.model.CustomerAPI;

import java.util.List;

public interface CustomerService {
    List<CustomerAPI> getAllCustomers();

    CustomerAPI getCustomerByLastName(String lastname);
}
