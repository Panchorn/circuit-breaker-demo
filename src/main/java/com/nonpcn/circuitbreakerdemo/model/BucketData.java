package com.nonpcn.circuitbreakerdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BucketData {

    private String customerName;
    private List<StuffData> stuffData;

}
