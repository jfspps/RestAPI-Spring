package com.example.restapidemo.services;

import com.example.restapidemo.api.mapper.CustomerMapper;
import com.example.restapidemo.api.model.CustomerAPI;
import com.example.restapidemo.domain.Customer;
import com.example.restapidemo.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    public static final String firstName = "James";
    public static final String lastName = "Smith";
    public static final Long id = 3L;

    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
    }

    @Test
    void getAllCustomers() {

        List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());

        when(customerRepository.findAll()).thenReturn(customers);

        List<CustomerAPI> customerAPIs = customerService.getAllCustomers();

        assertEquals(3, customerAPIs.size());
    }

    @Test
    void getCustomerByLastName() {
        Customer temp = new Customer();
        temp.setFirstname(firstName);
        temp.setLastname(lastName);
        temp.setId(id);

        when(customerRepository.findFirstByLastname(anyString())).thenReturn(temp);

        CustomerAPI customerAPI = customerService.getCustomerByLastName("someone");

        assertEquals(lastName, customerAPI.getLastname());
        assertEquals(id, customerAPI.getId());
        assertEquals(firstName, customerAPI.getFirstname());
    }
}