package com.example.controllers;



import com.example.JAXBmodel.CustomerAPI;
import com.example.JAXBmodel.CustomerListAPI;
import com.example.services.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// @Controller + @ResponseBody = @RestController (needed for Swagger to recognise CustomerAPI with http://localhost:8080/swagger-ui.html
@Controller
@ResponseBody
@RequestMapping("/api/customers")
@Tag(name = "customer-controller", description = "This is the Customer controller")
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
    @Operation(summary = "This lists all customers", description = "More detailed description goes here")
    public ResponseEntity<CustomerListAPI> getAllCustomers(){
        CustomerListAPI customerListAPI = new CustomerListAPI();
        customerListAPI.getCustomers().addAll(customerService.getAllCustomers());
        return new ResponseEntity<>(customerListAPI, HttpStatus.OK);
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
        CustomerListAPI customerListAPI = new CustomerListAPI();
        customerListAPI.getCustomers().addAll(customerService.getAllCustomersByLastName(lastName));
        return new ResponseEntity<>(customerListAPI, HttpStatus.OK);
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
     * @param ID id of the customer to be deleted
     * @return customer URL "/api/customers/id/customer_id"
     */
    @PutMapping({"/id/{ID}"})
    public ResponseEntity<CustomerAPI> updateCustomer(@PathVariable String ID, @RequestBody CustomerAPI customerAPI){
        return new ResponseEntity<>(customerService.saveCustomer(Long.valueOf(ID), customerAPI), HttpStatus.OK);
    }

    /**
     * Updates a customer and saves their records to the endpoint DB. Use "/api/customers/id/{someId}"
     * @param customerAPI body of the request (note that some properties can be null and are subsequently ignored)
     * @param ID id of the customer to be deleted
     * @return customer URL "/api/customers/id/customer_id"
     */
    @PatchMapping({"/id/{ID}"})
    public ResponseEntity<CustomerAPI> patchCustomer(@PathVariable String ID, @RequestBody CustomerAPI customerAPI){
        return new ResponseEntity<>(customerService.patchCustomer(Long.valueOf(ID), customerAPI), HttpStatus.OK);
    }

    /**
     * Deletes a customer from the endpoint DB. Use "/api/customers/id/{someId}"
     * @param ID id of the customer to be deleted
     * @return customer URL "/api/customers/id/customer_id"
     */
    @DeleteMapping({"/id/{ID}"})
    public ResponseEntity<Void> deleteCustomer(@PathVariable String ID){

        customerService.deleteCustomerById(Long.valueOf(ID));

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
