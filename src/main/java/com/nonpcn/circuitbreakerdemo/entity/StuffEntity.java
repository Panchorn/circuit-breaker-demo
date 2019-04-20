package com.nonpcn.circuitbreakerdemo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class StuffEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;

    protected StuffEntity() {

    }

    public StuffEntity(String name, Double price) {
        this.name = name;
        this.price = price;
    }

}
