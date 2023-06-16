package com.adan.savings.dto;

import com.adan.savings.entity.Customer;
import com.adan.savings.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

    private Long id;
    private Integer amount;
    private LocalDateTime timestamp;
    private PaymentMethod paymentMethod;
    private Long customerId;

}
