package com.example.restapidemo.controllers;

import com.example.restapidemo.api.model.CustomerAPI;
import com.example.restapidemo.services.CustomerService;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    public static final String firstName = "Daniel";

    MockMvc mockMvc;

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void testGetAllCustomers() throws Exception {
        CustomerAPI customerAPI1 = new CustomerAPI();
        customerAPI1.setFirstname(firstName);
        customerAPI1.setId(1L);

        CustomerAPI customerAPI2 = new CustomerAPI();
        customerAPI2.setFirstname("Bob");
        customerAPI2.setId(2L);

        List<CustomerAPI> customerAPIList = new ArrayList<>();
        customerAPIList.add(customerAPI1);
        customerAPIList.add(customerAPI2);

        when(customerService.getAllCustomers()).thenReturn(customerAPIList);

        mockMvc.perform(get("/api/customers/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2))); // $ is root
    }

    @Test
    public void testGetCustomerByLastName() throws Exception {
        CustomerAPI customerAPI1 = new CustomerAPI();
        customerAPI1.setFirstname(firstName);
        customerAPI1.setId(1L);

        when(customerService.getCustomerByLastName(anyString())).thenReturn(customerAPI1);

        mockMvc.perform(get("/api/customers/TheDude")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo(firstName)));
    }
}