package com.adan.savings.exception;

public class TransactionNotExistExcemption extends RuntimeException {
    public TransactionNotExistExcemption(String message){
        super(message);
    }
}
