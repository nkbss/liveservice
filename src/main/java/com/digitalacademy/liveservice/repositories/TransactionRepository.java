package com.digitalacademy.liveservice.repositories;

import com.digitalacademy.liveservice.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
