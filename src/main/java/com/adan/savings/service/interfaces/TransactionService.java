package com.adan.savings.service.interfaces;

import com.adan.savings.dto.TransactionDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TransactionService {
    List<TransactionDto> getAllTransactions();
    TransactionDto getTransactionById(Long id);
    TransactionDto createTransaction(TransactionDto transactionDto);
    TransactionDto updateTransaction(Long id, TransactionDto transactionDto);
    boolean deleteTransactionById(Long id);
}
