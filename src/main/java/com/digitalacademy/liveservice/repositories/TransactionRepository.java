package com.digitalacademy.liveservice.repositories;

import com.digitalacademy.liveservice.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    public static final String getTransactionByLiveId = "SELECT * FROM transaction WHERE live_id = :liveId";
    @Query(value = getTransactionByLiveId, nativeQuery = true)
    List<Transaction> getTransactionByLiveId(String liveId);

    public static final String sumPrice = "SELECT sum(total_price) FROM transaction WHERE live_id = :liveId";
    @Query(value = sumPrice, nativeQuery = true)
    Integer getTotalPrice(String liveId);
}
