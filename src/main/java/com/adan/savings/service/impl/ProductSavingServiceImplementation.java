package com.adan.savings.service.impl;


import com.adan.savings.dto.ProductSavingsDto;
import com.adan.savings.entity.Customer;
import com.adan.savings.entity.ProductSavings;
import com.adan.savings.exception.CustomerNotExistExcemption;
import com.adan.savings.exception.ProductSavingNotExistExcemption;
import com.adan.savings.repository.CustomerRepository;
import com.adan.savings.repository.ProductSavingRepository;
import com.adan.savings.service.interfaces.ProductSavingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProductSavingServiceImplementation implements ProductSavingService {

    private final ModelMapper modelMapper;
    private final ProductSavingRepository productSavingRepository;
    private final CustomerRepository customerRepository;


    @Override
    public List<ProductSavingsDto> getAllProductsSaving() {
        List<ProductSavings> productSavings = productSavingRepository.findAll();
        return productSavings.stream().map(productsSaving -> modelMapper.map(productsSaving, ProductSavingsDto.class)).collect(Collectors.toList());
    }


    @Override
    public ProductSavingsDto getProductSavingById(Long id) {
        ProductSavings productSavings = productSavingRepository.findById(id).orElseThrow(()
                -> new ProductSavingNotExistExcemption("Product Saving with given id do not exist")
        );
        return modelMapper.map(productSavings, ProductSavingsDto.class);
    }

    @Override
    public ProductSavingsDto createProductSaving(ProductSavingsDto productSavingsDto) {
        ProductSavings productSavings = new ProductSavings();
        Customer customer = customerRepository.findById(productSavingsDto.getCustomerId()).orElseThrow(
                () -> new CustomerNotExistExcemption("Customer with that Id not found")
        );

        productSavings.setAmount(productSavingsDto.getAmount());
        productSavings.setCustomerId(customer);
        productSavings.setType(productSavingsDto.getType());
        productSavings.setStartSavingDate(productSavingsDto.getStartSavingDate());
        productSavings.setEndSavingDate(productSavingsDto.getEndSavingDate());

        productSavingRepository.save(productSavings);

        return modelMapper.map(productSavings, ProductSavingsDto.class);
    }

    @Override
    public ProductSavingsDto updateProductSaving(Long id, ProductSavingsDto productSavingsDto) {
        Customer customer = customerRepository.findById(productSavingsDto.getCustomerId()).orElseThrow(
                () -> new CustomerNotExistExcemption("Customer with that Id not found")
        );
        ProductSavings productSavings = productSavingRepository.findById(id).orElseThrow(
                () -> new ProductSavingNotExistExcemption("Product saving with that id not found")
        );
        productSavings.setAmount(productSavingsDto.getAmount());
        productSavings.setCustomerId(customer);
        productSavings.setType(productSavingsDto.getType());
        productSavings.setStartSavingDate(productSavingsDto.getStartSavingDate());
        productSavings.setEndSavingDate(productSavingsDto.getEndSavingDate());

        return productSavingsDto;
    }


    @Override
    public boolean deleteProductSavingById(Long id) {
        ProductSavings productSavings = productSavingRepository.findById(id).orElseThrow(
                () -> new ProductSavingNotExistExcemption("Product with that Id not found")
        );
        productSavingRepository.delete(productSavings);
        return true;
    }

    @Override
    public Integer totalSavingPerPerson(Long id, LocalDate startDate, LocalDate endDate) {
        customerRepository.findById(id).orElseThrow(
                () -> new CustomerNotExistExcemption("Customer with that Id not found")
        );

        return productSavingRepository.totalSavingAmountPerPerson(id, startDate, endDate);
    }

    @Override
    public Integer totalSavingForAll(LocalDate startDate, LocalDate endDate) {
        return productSavingRepository.totalSavingAmountForAll(startDate, endDate);
    }
}
