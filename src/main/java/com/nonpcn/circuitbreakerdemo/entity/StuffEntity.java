package com.nonpcn.circuitbreakerdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class StuffEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stuffId;
    private String name = Strings.EMPTY;
    private Double price = 0.0;

    private Long bucketId;

    protected StuffEntity() {

    }

    public StuffEntity(String name, Double price, Long bucketId) {
        this.name = name;
        this.price = price;
        this.bucketId = bucketId;
    }

}
