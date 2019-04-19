package com.nonpcn.circuitbreakerdemo.service;

import com.nonpcn.circuitbreakerdemo.entity.BucketEntity;
import com.nonpcn.circuitbreakerdemo.mapper.BucketMapper;
import com.nonpcn.circuitbreakerdemo.model.BucketData;
import com.nonpcn.circuitbreakerdemo.repository.BucketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BucketService {

    private BucketRepository bucketRepository;

    @Autowired
    public BucketService(BucketRepository bucketRepository) {
        this.bucketRepository = bucketRepository;
    }

    public BucketData createBucket(String customerName) {
        System.out.println("Creating bucket for " + customerName);
        BucketEntity bucketEntity = bucketRepository.save(new BucketEntity(customerName));
        BucketData bucketData = BucketMapper.bucketDataMapper(bucketEntity);
        System.out.println("Bucket created :\n" + bucketEntity);
        return bucketData;
    }

}
