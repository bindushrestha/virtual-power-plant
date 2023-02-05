package com.example.virtualpowerplant.service;

import com.example.virtualpowerplant.entity.Battery;
import com.example.virtualpowerplant.pojo.BatteryStat;

import java.util.List;

public interface BatteryService {

    List<Battery> saveBatteries(List<Battery> batteries);

    BatteryStat getBatteryStatInRange(int startRange, int endRange);
}
