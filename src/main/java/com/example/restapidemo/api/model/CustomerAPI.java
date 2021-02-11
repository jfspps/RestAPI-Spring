package com.example.restapidemo.api.model;

import lombok.Data;

@Data
public class CustomerAPI {

    private Long id;
    private String firstname;
    private String lastname;

    // provides a URL to access the relevant entity via a HTTP request
    private String customer_url;
}
