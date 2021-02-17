package com.example.api.mapper;


import com.example.JAXBmodel.CustomerAPI;
import com.example.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerAPI customerToCustomerAPI(Customer customer);

    Customer customerAPIToCustomer(CustomerAPI customerAPI);
}
