package com.nonpcn.circuitbreakerdemo.service;

import com.nonpcn.circuitbreakerdemo.entity.BucketEntity;
import com.nonpcn.circuitbreakerdemo.entity.StuffEntity;
import com.nonpcn.circuitbreakerdemo.mapper.BucketMapper;
import com.nonpcn.circuitbreakerdemo.mapper.StuffMapper;
import com.nonpcn.circuitbreakerdemo.model.BucketData;
import com.nonpcn.circuitbreakerdemo.model.StuffData;
import com.nonpcn.circuitbreakerdemo.repository.BucketRepository;
import com.nonpcn.circuitbreakerdemo.repository.StuffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BucketService {

    private BucketRepository bucketRepository;
    private StuffRepository stuffRepository;

    @Autowired
    public BucketService(BucketRepository bucketRepository, StuffRepository stuffRepository) {
        this.bucketRepository = bucketRepository;
        this.stuffRepository = stuffRepository;
    }

    public BucketData createBucket(BucketData bucketData) {
        String customerName = bucketData.getCustomerName();
        List<StuffData> stuffDataList = bucketData.getStuffData();
        System.out.println("Creating bucket for " + customerName);
        if (stuffDataList == null || stuffDataList.isEmpty()) {
            return createBucketWithoutStuff(customerName);
        } else {
            return createBucketWithStuff(customerName, stuffDataList);
        }
    }

    private BucketData createBucketWithoutStuff(String customerName) {
        BucketEntity bucketEntity = bucketRepository.save(new BucketEntity(customerName));
        BucketData bucketData = BucketMapper.bucketDataMapper(bucketEntity);
        System.out.println("Bucket created : " + bucketEntity);
        return bucketData;
    }

    private BucketData createBucketWithStuff(String customerName, List<StuffData> stuffDataList) {
        Optional<BucketEntity> _bucketEntity = bucketRepository.findByCustomerName(customerName);
        BucketEntity bucketEntity;
        if (!_bucketEntity.isPresent()) {
            bucketEntity = bucketRepository.save(new BucketEntity(customerName));
        }
        else {
            bucketEntity = _bucketEntity.get();
        }

        List<StuffEntity> stuffEntityList = saveStuffs(StuffMapper.stuffEntityListMapper(stuffDataList, bucketEntity.getBucketId()));
        bucketEntity.setStuffs(stuffEntityList);

        BucketData bucketData = BucketMapper.bucketDataMapper(bucketEntity);
        System.out.println("Bucket created : " + bucketEntity);
        return bucketData;
    }

    private List<StuffEntity> saveStuffs(List<StuffEntity> stuffEntityList) {
        return stuffRepository.saveAll(stuffEntityList);
    }

    public List<BucketData> getBuckets() {
        System.out.println("Finding all bucket");
        List<BucketEntity> bucketEntityList = bucketRepository.findAll();

        bucketEntityList.stream().forEach(bucketEntity -> {
            Optional<List<StuffEntity>> _stuffEntity = stuffRepository.findByBucketId(bucketEntity.getBucketId());
            if (_stuffEntity.isPresent()) {
                bucketEntity.setStuffs(_stuffEntity.get());
            }
        });

        List<BucketData> bucketDataList = BucketMapper.bucketDataListMapper(bucketEntityList);
        System.out.println("All bucket " + bucketEntityList);
        return bucketDataList;
    }

}
