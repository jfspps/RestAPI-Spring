package com.example.restapidemo.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CustomerListAPI {

    private List<CustomerAPI> customers;
}
