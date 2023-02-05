package com.example.virtualpowerplant.controller;

import com.example.virtualpowerplant.entity.Battery;
import com.example.virtualpowerplant.pojo.BatteryStat;
import com.example.virtualpowerplant.service.BatteryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BatteryController.class)
public class BatteryControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BatteryService batteryService;

    @Test
    public void test_saveBatteries() throws Exception {
        List<Battery> batteryList = Arrays.asList(Battery.builder().Name("bat1").postCode(1).wattCapacity(50).build(),
                Battery.builder().Name("bat2").postCode(2).wattCapacity(25).build());
        List<Battery> expected = new ArrayList<>();
        expected.addAll(batteryList);
        expected.get(0).setId(1);
        expected.get(1).setId(2);
        when(batteryService.saveBatteries(batteryList)).thenReturn(expected);
        MvcResult result = mvc.perform(post("/api/v1/batteries").contentType(MediaType.APPLICATION_JSON).content(mapToJson(batteryList)))
                .andExpect(status().is2xxSuccessful()).andReturn();
        verify(batteryService, times(1)).saveBatteries(batteryList);
        Assert.assertEquals(result.getResponse().getContentAsString(), mapToJson(expected));
    }

    @Test
    public void test_getBatteryStat() throws Exception {

        BatteryStat batteryStat = BatteryStat.builder().batteryNames(Arrays.asList("bat1", "bat2")).averageCapacity(25)
                .totalCapacity(50).build();
        when(batteryService.getBatteryStatInRange(anyInt(),anyInt())).thenReturn(batteryStat);
       String response = mvc.perform(get("/api/v1/batteries-stat")
                        .param("startRangePostCode","1")
                        .param("endRangePostCode", "5"))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        assertEquals(response, mapToJson(batteryStat));

    }
    private String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }


}
