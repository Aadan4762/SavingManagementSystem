package com.adan.savings.controller;

import com.adan.savings.dto.CustomerDto;
import com.adan.savings.dto.ProductSavingsDto;
import com.adan.savings.service.interfaces.ProductSavingService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/productSavings")
@RequiredArgsConstructor
public class ProductSavingController {

    private final ProductSavingService productSavingService;

    @PostMapping("/create")
    public ResponseEntity<ProductSavingsDto> createProductSavings(@RequestBody ProductSavingsDto productSavingsDto) {
        ProductSavingsDto response = productSavingService.createProductSaving(productSavingsDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductSavingsDto>> getAllProductSavings() {
        List<ProductSavingsDto> response = productSavingService.getAllProductsSaving();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductSavingsDto> getProductSavingById(@PathVariable("id") Long id) {
        ProductSavingsDto response = productSavingService.getProductSavingById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductSavingsDto> updateProductSavings(@PathVariable("id") Long id, @RequestBody ProductSavingsDto productSavingsDto) {
        ProductSavingsDto response = productSavingService.updateProductSaving(id, productSavingsDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProductSaving(@PathVariable("id") Long id) {
        boolean success = productSavingService.deleteProductSavingById(id);
        return ResponseEntity.ok(success);
    }

    @GetMapping("/total-saving-per-person")
    public ResponseEntity<Integer> getTotalSavingPerPerson(@PathParam("id") Long id, @PathParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate, @PathParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        Integer response = productSavingService.totalSavingPerPerson(id, startDate, endDate);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/total-saving-for-all")
    public ResponseEntity<Integer> getTotalSavingForAll(@PathParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate, @PathParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        Integer response = productSavingService.totalSavingForAll(startDate, endDate);

        return ResponseEntity.ok(response);
    }

}
