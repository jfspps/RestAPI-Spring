package com.example.restapidemo.controllers;

import com.example.restapidemo.api.model.CustomerAPI;
import com.example.restapidemo.api.model.CustomerListAPI;
import com.example.restapidemo.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Returns (currently) a JSON of all fruit customers with their properties
     * @return JSON formatted list
     */
    @GetMapping("/")
    public ResponseEntity<CustomerListAPI> getAllCustomers(){
        return new ResponseEntity<>(new CustomerListAPI(customerService.getAllCustomers()), HttpStatus.OK);
    }

    /**
     * Returns (currently) a JSON of the first customer found, with the last name given, with their properties
     * @param lastName customer's last name
     * @return JSON formatted list
     */
    @GetMapping("/{lastName}")
    public ResponseEntity<CustomerAPI> getCustomerByLastName(@PathVariable String lastName){
        return new ResponseEntity<>(customerService.getCustomerByLastName(lastName), HttpStatus.OK);
    }

    /**
     * Returns (currently) a JSON of the first customer found, with the ID given, with their properties
     * @param ID customer's ID
     * @return JSON formatted list
     */
    @GetMapping("/id/{ID}")
    public ResponseEntity<CustomerAPI> getCustomerById(@PathVariable String ID){
        return new ResponseEntity<>(customerService.getCustomerById(Long.valueOf(ID)), HttpStatus.OK);
    }

    /**
     * Returns (currently) a JSON of all customers found, with the last name given, with their properties
     * @param lastName customers' last name
     * @return JSON formatted list
     */
    @GetMapping("/{lastName}/all")
    public ResponseEntity<CustomerListAPI> getAllCustomersByLastName(@PathVariable String lastName){
        return new ResponseEntity<>(new CustomerListAPI(customerService.getAllCustomersByLastName(lastName)), HttpStatus.OK);
    }

    /**
     * Builds a new customer and saves their records to the endpoint DB. Use "/api/customers/"
     * @param customerAPI body of the request
     * @return customer URL "/api/customers/id/customer_id"
     */
    @PostMapping({"", "/"})
    public ResponseEntity<CustomerAPI> createNewCustomer(@RequestBody CustomerAPI customerAPI){
        return new ResponseEntity<>(customerService.createCustomer(customerAPI), HttpStatus.CREATED);
    }

    /**
     * Updates a customer and saves their records to the endpoint DB. Use "/api/customers/id/{someId}"
     * @param customerAPI body of the request
     * @return customer URL "/api/customers/id/customer_id"
     */
    @PutMapping({"/id/{ID}"})
    public ResponseEntity<CustomerAPI> updateCustomer(@PathVariable String ID, @RequestBody CustomerAPI customerAPI){
        return new ResponseEntity<>(customerService.saveCustomer(Long.valueOf(ID), customerAPI), HttpStatus.OK);
    }
}
