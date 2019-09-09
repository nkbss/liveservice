package com.digitalacademy.liveservice.repositories;


import com.digitalacademy.liveservice.model.LiveStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LiveStockRepository extends JpaRepository<LiveStock,Integer> {

    public static final String allLiveStock = "SELECT * From livestock where user_id= :userId AND live_id = :liveId ";
    @Query(value = allLiveStock, nativeQuery = true)
    List<LiveStock> findAllLiveStock(String userId,String liveId);

    public static final String liveStock = "SELECT * From livestock where user_id= :userId AND live_id = :liveId AND stock_id = :stockId ";
    @Query(value = liveStock, nativeQuery = true)
    LiveStock getLiveStock(String userId,String liveId, String stockId);

//    LiveStock findByLiveId(String liveId);

    public static final String findLive = "SELECT * From livestock where live_id = :liveId and stock_id = :stockId";
    @Query(value = findLive, nativeQuery = true)
    LiveStock findLiveByStockId(String liveId,int stockId);
//    public static final String closeDeal = "UPDATE livestock SET close_deal = :closeDeal , deep_link = :deepLink WHERE user_id= :userId AND live_id = :liveId";
//    @Query(value = closeDeal,nativeQuery = true)
//     closeDeals(int closeDeal,String deepLink,String userId,String liveId);
}
