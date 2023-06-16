package com.adan.savings.dto;

import com.adan.savings.entity.Customer;
import com.adan.savings.enums.SavingType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductSavingsDto {

    private Long id;
    private Integer amount;
    private Long customerId;
    private SavingType type;
    private LocalDate startSavingDate;
    private LocalDate endSavingDate;
}
