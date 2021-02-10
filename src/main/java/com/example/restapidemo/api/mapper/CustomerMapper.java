package com.example.restapidemo.api.mapper;

import com.example.restapidemo.api.model.CustomerAPI;
import com.example.restapidemo.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerAPI customerToCustomerAPI(Customer customer);
}
