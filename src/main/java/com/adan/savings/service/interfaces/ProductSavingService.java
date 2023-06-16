package com.adan.savings.service.interfaces;
import com.adan.savings.dto.ProductSavingsDto;

import java.time.LocalDate;
import java.util.List;

public interface ProductSavingService {

    List<ProductSavingsDto> getAllProductsSaving();
    ProductSavingsDto getProductSavingById(Long id);
    ProductSavingsDto createProductSaving(ProductSavingsDto productSavingsDto);
    ProductSavingsDto updateProductSaving(Long id, ProductSavingsDto productSavingsDto);
    boolean deleteProductSavingById(Long id);
    Integer totalSavingPerPerson(Long id, LocalDate startDate, LocalDate endDate);
    Integer totalSavingForAll(LocalDate startDate, LocalDate endDate);
}
