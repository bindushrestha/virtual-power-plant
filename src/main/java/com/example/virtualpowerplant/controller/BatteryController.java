package com.example.virtualpowerplant.controller;


import com.example.virtualpowerplant.entity.Battery;
import com.example.virtualpowerplant.pojo.BatteryStat;
import com.example.virtualpowerplant.service.BatteryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class BatteryController {

    private BatteryService batteryService;

    @PostMapping("/batteries")
    public ResponseEntity<List<Battery>> saveBatteries(@RequestBody List<Battery> batteries){
            return new ResponseEntity<>(batteryService.saveBatteries(batteries), HttpStatus.CREATED);
    }

    @GetMapping("/batteries-stat")
    public ResponseEntity<BatteryStat> getBatteryStat(@RequestParam(name = "startRangePostCode", required = true) int startRangePostCode,
                                         @RequestParam(name = "endRangePostCode", required = true) int endRangePostCode){
        return new ResponseEntity<>(batteryService.getBatteryStatInRange(startRangePostCode, endRangePostCode), HttpStatus.OK);
    }
}
