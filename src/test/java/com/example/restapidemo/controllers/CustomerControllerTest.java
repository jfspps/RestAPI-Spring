package com.example.restapidemo.controllers;

import com.example.restapidemo.api.model.CustomerAPI;
import com.example.restapidemo.domain.Customer;
import com.example.restapidemo.services.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    public static final String firstName = "Daniel";
    public static final String lastName = "Smith";

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

    @Test
    public void testGetCustomerById() throws Exception {
        CustomerAPI customerAPI1 = new CustomerAPI();
        customerAPI1.setFirstname(firstName);
        customerAPI1.setId(1L);

        when(customerService.getCustomerById(anyLong())).thenReturn(customerAPI1);

        mockMvc.perform(get("/api/customers/id/23")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo(firstName)));
    }

    @Test
    public void testGetAllCustomersByLastName() throws Exception {
        CustomerAPI customerAPI1 = new CustomerAPI();
        customerAPI1.setLastname(lastName);
        customerAPI1.setFirstname("John");
        customerAPI1.setId(1L);

        CustomerAPI customerAPI2 = new CustomerAPI();
        customerAPI2.setFirstname("Bob");
        customerAPI2.setLastname(lastName);
        customerAPI2.setId(2L);

        List<CustomerAPI> customerAPIList = new ArrayList<>();
        customerAPIList.add(customerAPI1);
        customerAPIList.add(customerAPI2);

        when(customerService.getAllCustomersByLastName(anyString())).thenReturn(customerAPIList);

        mockMvc.perform(get("/api/customers/Smith/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2))); // $ is root
    }

    @Test
    public void createNewCustomer() throws Exception {
        //given
        CustomerAPI customer = new CustomerAPI();
        customer.setFirstname("Mary");
        customer.setLastname("Magdalen");

        CustomerAPI savedCustomer = new CustomerAPI();
        savedCustomer.setFirstname(customer.getFirstname());
        savedCustomer.setLastname(customer.getLastname());
        savedCustomer.setCustomer_url("/api/customers/id/1");

        when(customerService.createCustomer(customer)).thenReturn(savedCustomer);

        //when/then
        mockMvc.perform(post("/api/customers/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstname", equalTo("Mary")))
                .andExpect(jsonPath("$.customer_url", equalTo("/api/customers/id/1")));
    }

    /**
     * Build a JSON string from an object
     * @param obj object to be converted to JSON (Spring parses the Body
     * @return JSON list
     */
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void saveCustomer() throws Exception {
        //given
        CustomerAPI customer = new CustomerAPI();
        customer.setFirstname("Mary");
        customer.setLastname("Magdalen");
        customer.setId(1L);

        CustomerAPI savedCustomer = new CustomerAPI();
        savedCustomer.setFirstname(customer.getFirstname());
        savedCustomer.setLastname(customer.getLastname());
        savedCustomer.setCustomer_url("/api/customers/id/1");

        when(customerService.saveCustomer(1L, customer)).thenReturn(savedCustomer);

        // testing a PUT request
        mockMvc.perform(put("/api/customers/id/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo("Mary")))
                .andExpect(jsonPath("$.lastname", equalTo("Magdalen")))
                .andExpect(jsonPath("$.customer_url", equalTo("/api/customers/id/1")));
    }

    @Test
    public void patchCustomer() throws Exception {
        //given
        CustomerAPI customer = new CustomerAPI();
        customer.setFirstname("Mary");
        customer.setLastname("OldLastName");
        customer.setId(1L);

        CustomerAPI savedCustomer = new CustomerAPI();
        savedCustomer.setFirstname(customer.getFirstname());
        savedCustomer.setLastname("NewLastName");
        savedCustomer.setCustomer_url("/api/customers/id/1");

        when(customerService.patchCustomer(anyLong(), any(CustomerAPI.class))).thenReturn(savedCustomer);

        // testing a PUT request
        mockMvc.perform(patch("/api/customers/id/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo("Mary")))
                .andExpect(jsonPath("$.lastname", equalTo("NewLastName")))
                .andExpect(jsonPath("$.customer_url", equalTo("/api/customers/id/1")));
    }
}