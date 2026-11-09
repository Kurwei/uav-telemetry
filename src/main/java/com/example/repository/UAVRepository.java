package com.example.repository; 

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.UAV;

public interface UAVRepository extends JpaRepository<UAV, Long> {
    /*
        @Query("SELECT u FROM UAV u WHERE u.model LIKE %:keyword%")
        List<UAV> searchByModelKeyword(@Param("keyword") String keyword); 
    */
    UAV findByApiKey(String apiKey);
}