package com.nonpcn.circuitbreakerdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StuffData {

    private String name;
    private Double price;
    private Long bucketId;

}
