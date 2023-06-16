package com.adan.savings.service.impl;



import com.adan.savings.dto.TransactionDto;
import com.adan.savings.entity.Customer;
import com.adan.savings.entity.Transaction;
import com.adan.savings.exception.CustomerNotExistExcemption;
import com.adan.savings.exception.TransactionNotExistExcemption;
import com.adan.savings.repository.CustomerRepository;
import com.adan.savings.repository.TransactionRepository;
import com.adan.savings.service.interfaces.TransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImplementation implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final ModelMapper modelMapper;

    private final CustomerRepository customerRepository;

    @Override
    public List<TransactionDto> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactions.stream().map(transaction -> modelMapper.map(transaction, TransactionDto.class)).collect(Collectors.toList());
    }

    @Override
    public TransactionDto getTransactionById(Long id) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(
                () -> new TransactionNotExistExcemption("Transaction with given id does not exist")
        );
        return modelMapper.map(transaction, TransactionDto.class);
    }


    @Override
    public TransactionDto createTransaction(TransactionDto transactionDto) {
        Customer customer = customerRepository.findById(transactionDto.getCustomerId()).orElseThrow(
                () -> new CustomerNotExistExcemption("Customer with that Id not found")
        );
        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDto.getAmount());
        transaction.setTimestamp(transactionDto.getTimestamp());
        transaction.setPaymentMethod(transactionDto.getPaymentMethod());
        transaction.setCustomerId(customer);

        transactionRepository.save(transaction);

        return modelMapper.map(transaction, TransactionDto.class);
    }

    @Override
    public TransactionDto updateTransaction(Long id, TransactionDto transactionDto) {
        Customer customer = customerRepository.findById(transactionDto.getCustomerId()).orElseThrow(
                () -> new CustomerNotExistExcemption("Customer with that Id not found")
        );
        Transaction transaction = transactionRepository.findById(id).orElseThrow(
                () -> new TransactionNotExistExcemption("Transaction with given Id does not exist")
        );
        transaction.setAmount(transactionDto.getAmount());
        transaction.setTimestamp(transactionDto.getTimestamp());
        transaction.setPaymentMethod(transactionDto.getPaymentMethod());
        transaction.setCustomerId(customer);

        return transactionDto;

    }
    @Override
    public boolean deleteTransactionById(Long id) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(
                () -> new TransactionNotExistExcemption("Transaction with given Id not available")
        );
        transactionRepository.delete(transaction);
        return true;
    }
}
