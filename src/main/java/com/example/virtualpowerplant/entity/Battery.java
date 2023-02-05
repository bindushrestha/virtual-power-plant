package com.example.virtualpowerplant.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Battery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String Name;
    private int postCode;
    private double wattCapacity;

}
