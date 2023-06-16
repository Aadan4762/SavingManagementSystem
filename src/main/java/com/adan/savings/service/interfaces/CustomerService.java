package com.adan.savings.service.interfaces;

import com.adan.savings.dto.CustomerDto;
import com.adan.savings.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> getAllCustomers();
    CustomerDto getCustomerById(Long id);
    CustomerDto createCustomer(CustomerDto customerDto);
    CustomerDto updateCustomer(Long id, CustomerDto customerDto);
    boolean deleteCustomerById(Long id);

}
