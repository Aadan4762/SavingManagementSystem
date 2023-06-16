package com.adan.savings.controller;

import com.adan.savings.dto.TransactionDto;
import com.adan.savings.service.interfaces.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;


    @PostMapping("/create")
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionDto transactionDto) {
        TransactionDto response = transactionService.createTransaction(transactionDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<TransactionDto>> getAllTransactionSavings() {
        List<TransactionDto> response = transactionService.getAllTransactions();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> getTransactionById(@PathVariable("id") Long id) {
        TransactionDto response = transactionService.getTransactionById(id);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<TransactionDto> updateTransaction(@PathVariable("id") Long id, @RequestBody TransactionDto transactionDto) {
        TransactionDto response = transactionService.updateTransaction(id, transactionDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteTransaction(@PathVariable("id") Long id) {
        boolean success = transactionService.deleteTransactionById(id);
        return ResponseEntity.ok(success);
    }

}
