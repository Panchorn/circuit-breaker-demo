package com.nonpcn.circuitbreakerdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
public class BucketData {

    private String customerName;
    private List<StuffData> stuffData;

}
