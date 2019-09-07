package com.digitalacademy.liveservice.repositories;

import com.digitalacademy.liveservice.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    public static final String sumPrice = "SELECT sum(total_price) FROM transaction;";
    @Query(value = sumPrice, nativeQuery = true)
    Integer getTotalPrice();
}
