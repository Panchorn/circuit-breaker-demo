package com.nonpcn.circuitbreakerdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class StuffData {

    private String name;
    private Double price;

}
