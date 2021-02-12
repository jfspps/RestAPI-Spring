package com.example.restapidemo.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CustomerAPI {

    private Long id;

    private String firstname;

    @Schema(description = "The customer's last name", defaultValue = "Bloggs", required = true)
    private String lastname;

    // provides a URL to access the relevant entity via a HTTP request
    private String customer_url;
}
