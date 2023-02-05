package com.example.virtualpowerplant.service;

import com.example.virtualpowerplant.entity.Battery;
import com.example.virtualpowerplant.pojo.BatteryStat;
import com.example.virtualpowerplant.repository.BatteryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BatteryServiceImpl implements BatteryService{

    private BatteryRepository batteryRepository;

    @Override
    public List<Battery> saveBatteries(List<Battery> batteries) {
        return batteryRepository.saveAll(batteries);
    }

    @Override
    public BatteryStat getBatteryStatInRange(int startRange, int endRange) {
       List<Battery> batteryList = batteryRepository.findByPostCodeBetween(startRange, endRange);
       return BatteryStat.builder().totalCapacity(batteryList.stream().mapToDouble(Battery::getWattCapacity).sum())
               .averageCapacity(batteryList.stream().mapToDouble(Battery::getWattCapacity).average().orElse(0.0))
               .batteryNames(batteryList.stream().map(b->b.getName()).sorted().collect(Collectors.toList())).build();
    }

}
