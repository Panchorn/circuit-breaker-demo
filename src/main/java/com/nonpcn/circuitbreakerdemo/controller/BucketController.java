package com.nonpcn.circuitbreakerdemo.controller;

import com.nonpcn.circuitbreakerdemo.model.BucketData;
import com.nonpcn.circuitbreakerdemo.service.BucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/bucket")
public class BucketController {

    private BucketService bucketService;

    @Autowired
    public BucketController(BucketService bucketService) {
        this.bucketService = bucketService;
    }

    @PostMapping
    public BucketData createBucket(@RequestBody BucketData bucketData) {
        return bucketService.createBucket(bucketData.getCustomerName());
    }

}
