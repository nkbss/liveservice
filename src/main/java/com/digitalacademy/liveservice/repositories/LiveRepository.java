package com.digitalacademy.liveservice.repositories;

import com.digitalacademy.liveservice.model.Live;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LiveRepository extends JpaRepository<Live, Integer> {
    public static final String getLive = "SELECT * From live where user_id= :userId AND live_id = :liveId ";
    @Query(value = getLive, nativeQuery = true)
    Live getLiveByLiveId(String userId, String liveId);
}
