package com.example.virtualpowerplant.repository;

import com.example.virtualpowerplant.entity.Battery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatteryRepository extends JpaRepository<Battery, Long> {
    List<Battery> findByPostCodeBetween(int startRange, int endRange);
}
