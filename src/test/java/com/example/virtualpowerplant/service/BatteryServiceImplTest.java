package com.example.virtualpowerplant.service;

import com.example.virtualpowerplant.entity.Battery;
import com.example.virtualpowerplant.pojo.BatteryStat;
import com.example.virtualpowerplant.repository.BatteryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class BatteryServiceImplTest {

    BatteryService batteryService;
    @Mock
    BatteryRepository batteryRepository;
    @Before
    public void setup() {
        batteryService = new BatteryServiceImpl(batteryRepository);
    }
    @Test
    public void test_saveBatteries(){
        List<Battery> batteryList = Arrays.asList(Battery.builder().Name("bat1").postCode(1).wattCapacity(50).build(),
                Battery.builder().Name("bat2").postCode(2).wattCapacity(25).build());
        batteryService.saveBatteries(batteryList);
        verify(batteryRepository, times(1)).saveAll(batteryList);
    }

    @Test
    public void test_getBatteryStatInRange(){
        List<Battery> batteryList = Arrays.asList(Battery.builder().Name("bat1").postCode(1).wattCapacity(50).build(),
                Battery.builder().Name("bat2").postCode(2).wattCapacity(25).build());
        BatteryStat batteryStat = BatteryStat.builder().batteryNames(Arrays.asList("bat1", "bat2")).averageCapacity(37.5)
                .totalCapacity(75).build();
        when(batteryRepository.findByPostCodeBetween(anyInt(), anyInt())).thenReturn(batteryList);
        BatteryStat stat = batteryService.getBatteryStatInRange(1,2);
        assertEquals(batteryStat, stat);
    }
}
