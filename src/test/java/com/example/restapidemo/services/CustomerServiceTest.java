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
import static org.mockito.ArgumentMatchers.*;
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
    void getAllCustomersByLastName() {

        List<Customer> customers = Arrays.asList(new Customer());

        when(customerRepository.findAllByLastname(anyString())).thenReturn(customers);

        List<CustomerAPI> customerAPIs = customerService.getAllCustomersByLastName("TheDude");

        assertEquals(1, customerAPIs.size());
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

    @Test
    void getCustomerById() {
        Customer temp = new Customer();
        temp.setFirstname(firstName);
        temp.setLastname(lastName);
        temp.setId(id);

        when(customerRepository.findCustomerById(anyLong())).thenReturn(temp);

        CustomerAPI customerAPI = customerService.getCustomerById(300L);

        assertEquals(lastName, customerAPI.getLastname());
        assertEquals(id, customerAPI.getId());
        assertEquals(firstName, customerAPI.getFirstname());
    }

    @Test
    void createNewCustomer() throws Exception {
         CustomerAPI customerAPI = new CustomerAPI();
         customerAPI.setLastname(lastName);

         Customer saved = new Customer();
         saved.setFirstname(firstName);
         saved.setLastname(lastName);
         saved.setId(id);

         when(customerRepository.save(any(Customer.class))).thenReturn(saved);

         CustomerAPI savedAPI = customerService.createCustomer(customerAPI);

         assertEquals(lastName, savedAPI.getLastname());
         assertEquals("/api/customers/id/" + id, savedAPI.getCustomer_url());
    }

    @Test
    void saveCustomer() throws Exception {
        CustomerAPI customerAPI = new CustomerAPI();
        customerAPI.setLastname(lastName);

        Customer saved = new Customer();
        saved.setFirstname(firstName);
        saved.setLastname(lastName);
        saved.setId(id);

        when(customerRepository.save(any(Customer.class))).thenReturn(saved);

        CustomerAPI savedAPI = customerService.saveCustomer(id, customerAPI);

        assertEquals(lastName, savedAPI.getLastname());
        assertEquals("/api/customers/id/" + id, savedAPI.getCustomer_url());
    }
}