package com.example.virtualpowerplant.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class BatteryStat {

    double totalCapacity;
    double averageCapacity;
    List<String> batteryNames;
}
