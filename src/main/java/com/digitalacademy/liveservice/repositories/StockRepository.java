package com.digitalacademy.liveservice.repositories;

import com.digitalacademy.liveservice.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Integer> {
    Stock findById(int stockId);

    public static final String allStock = "SELECT * From stock where user_id= :userId";
    @Query(value = allStock, nativeQuery = true)
    List<Stock> findAllStockByUserId(String userId);

    public static final String getStockByStockId = "SELECT * From stock where user_id= :userId and stock_id = :stockId ";
    @Query(value = getStockByStockId, nativeQuery = true)
    Stock getStockByStockId(String userId, int stockId);

    Stock findByStockId(int stockId);
}

