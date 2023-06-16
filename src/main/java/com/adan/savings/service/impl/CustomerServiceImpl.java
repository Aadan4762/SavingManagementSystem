package com.adan.savings.service.impl;

import com.adan.savings.dto.CustomerDto;
import com.adan.savings.entity.Customer;
import com.adan.savings.exception.CustomerNotExistExcemption;
import com.adan.savings.repository.CustomerRepository;
import com.adan.savings.service.interfaces.CustomerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customer -> modelMapper.map(customer, CustomerDto.class)).collect(Collectors.toList());
    }

    @Override
    public CustomerDto getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new CustomerNotExistExcemption("Customer with that Id not found")
        );

        return modelMapper.map(customer, CustomerDto.class);
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setEmail(customerDto.getLastName());
        customer.setMemberNumber(customer.getMemberNumber());

        customerRepository.save(customer);

        return customerDto;
    }

    @Override
    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {
        Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new CustomerNotExistExcemption("Customer with that Id not found")
        );
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setEmail(customerDto.getLastName());
        customer.setMemberNumber(customer.getMemberNumber());

        return modelMapper.map(customer, CustomerDto.class);
    }

    @Override
    public boolean deleteCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new CustomerNotExistExcemption("Customer with that Id not found")
        );
        customerRepository.delete(customer);
        return true;
    }

}
