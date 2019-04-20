package com.nonpcn.circuitbreakerdemo.service;

import com.nonpcn.circuitbreakerdemo.entity.BucketEntity;
import com.nonpcn.circuitbreakerdemo.entity.StuffEntity;
import com.nonpcn.circuitbreakerdemo.mapper.BucketMapper;
import com.nonpcn.circuitbreakerdemo.mapper.StuffMapper;
import com.nonpcn.circuitbreakerdemo.model.BucketData;
import com.nonpcn.circuitbreakerdemo.model.StuffData;
import com.nonpcn.circuitbreakerdemo.repository.BucketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BucketService {

    private BucketRepository bucketRepository;

    @Autowired
    public BucketService(BucketRepository bucketRepository) {
        this.bucketRepository = bucketRepository;
    }

    public BucketData createBucket(BucketData bucketData) {
        String customerName = bucketData.getCustomerName();
        List<StuffData> stuffDataList = bucketData.getStuffData();
        System.out.println("Creating bucket for " + customerName);
        if (stuffDataList == null || stuffDataList.isEmpty()) {
            return createBucketWithoutStuff(customerName);
        }
        else {
            return createBucketWithStuff(customerName, stuffDataList);
        }
    }

    private BucketData createBucketWithoutStuff(String customerName) {
        BucketEntity bucketEntity = bucketRepository.save(new BucketEntity(customerName));
        BucketData bucketData = BucketMapper.bucketDataMapper(bucketEntity);
        System.out.println("Bucket created :\n" + bucketEntity);
        return bucketData;
    }

    private BucketData createBucketWithStuff(String customerName, List<StuffData> stuffDataList) {
        BucketEntity bucketEntity = bucketRepository.findByCustomerName(customerName).orElse(new BucketEntity(customerName));
        List<StuffEntity> stuffEntities = StuffMapper.stuffEntityListMapper(stuffDataList);
        bucketEntity.setStuffEntities(stuffEntities);
        bucketRepository.save(bucketEntity);
        BucketData bucketData = BucketMapper.bucketDataMapper(bucketEntity);
        System.out.println("Bucket created :\n" + bucketEntity);
        return bucketData;
    }

}
