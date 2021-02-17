package com.example.api.mapper;

import com.example.api.model.CustomerAPI;
import com.example.domain.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest {

    public static final String firstName = "James";
    public static final String lastName = "Smith";
    public static final Long id = 3L;

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @BeforeEach
    void setUp() {
    }

    @Test
    void customerToCustomerAPI() {
        Customer customer = new Customer();
        customer.setFirstname(firstName);
        customer.setLastname(lastName);
        customer.setId(id);

        CustomerAPI customerAPI = customerMapper.customerToCustomerAPI(customer);

        assertEquals(firstName, customerAPI.getFirstname());
        assertEquals(lastName, customerAPI.getLastname());
        assertEquals(id, customerAPI.getId());
    }
}