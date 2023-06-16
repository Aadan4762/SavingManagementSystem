package com.adan.savings.entity;

import com.adan.savings.enums.SavingType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_savings")
public class ProductSavings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Long id;
    @Column(name = "amount")
    private Integer amount;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customerId;
    @Enumerated(EnumType.STRING)
    @Column(name = "saving_type")
    private SavingType type;
    @Column(name = "startSavingDate")
    private LocalDate startSavingDate;
    @Column(name = "endSavingDate")
    private LocalDate endSavingDate;
}
