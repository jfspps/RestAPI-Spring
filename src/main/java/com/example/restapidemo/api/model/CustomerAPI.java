package com.example.restapidemo.api.model;

import lombok.Data;

@Data
public class CustomerAPI {

    private Long id;
    private String firstname;
    private String lastname;
    private String customer_url;
}
