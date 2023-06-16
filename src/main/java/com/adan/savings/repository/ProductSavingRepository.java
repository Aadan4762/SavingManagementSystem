package com.adan.savings.repository;

import com.adan.savings.entity.ProductSavings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ProductSavingRepository extends JpaRepository<ProductSavings, Long> {
    @Query("SELECT SUM(p.amount) AS total FROM ProductSavings p WHERE p.customerId.id = :id AND p.startSavingDate = :startDate AND p.endSavingDate = :endDate")
    Integer totalSavingAmountPerPerson(@Param("id") Long id, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT SUM(p.amount) AS total FROM ProductSavings p WHERE p.startSavingDate = :startDate AND p.endSavingDate = :endDate")
    Integer totalSavingAmountForAll(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
