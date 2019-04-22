package com.nonpcn.circuitbreakerdemo.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.nonpcn.circuitbreakerdemo.entity.BucketEntity;
import com.nonpcn.circuitbreakerdemo.entity.StuffEntity;
import com.nonpcn.circuitbreakerdemo.mapper.BucketMapper;
import com.nonpcn.circuitbreakerdemo.mapper.StuffMapper;
import com.nonpcn.circuitbreakerdemo.model.BucketData;
import com.nonpcn.circuitbreakerdemo.model.StuffData;
import com.nonpcn.circuitbreakerdemo.repository.BucketRepository;
import com.nonpcn.circuitbreakerdemo.repository.StuffRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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
        } else {
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
        setStuffsToBuckets(bucketEntityList);
        List<BucketData> bucketDataList = BucketMapper.bucketDataListMapper(bucketEntityList);
        System.out.println("All bucket " + bucketEntityList);
        return bucketDataList;
    }

    public BucketData getBucket(String customerName) {
        System.out.println("Finding bucket for : " + customerName);
        BucketEntity bucketEntity = findBucketEntity(customerName);
        BucketData bucketData = bucketEntity != null ? BucketMapper.bucketDataMapper(bucketEntity) : null;
        System.out.println("Bucket detail : " + bucketEntity);
        return bucketData;
    }

    private BucketEntity findBucketEntity(String customerName) {
        Optional<BucketEntity> _bucketEntity = bucketRepository.findByCustomerName(customerName);
        BucketEntity bucketEntity = null;
        if (_bucketEntity.isPresent()) {
            bucketEntity = _bucketEntity.get();
            setStuffsToBucket(bucketEntity);
        }
        return bucketEntity;
    }

    private void setStuffsToBuckets(List<BucketEntity> bucketEntityList) {
        bucketEntityList.stream().forEach(bucketEntity -> setStuffsToBucket(bucketEntity));
    }

    private void setStuffsToBucket(BucketEntity bucketEntity) {
        Optional<List<StuffEntity>> _stuffEntity = stuffRepository.findByBucketId(bucketEntity.getBucketId());
        if (_stuffEntity.isPresent()) {
            bucketEntity.setStuffs(_stuffEntity.get());
        }
    }

    @HystrixCommand(
            fallbackMethod = "fallbackGetBucketsWithCircuit",
            threadPoolKey = "getBucketsWithCircuitThreadPool",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "20"),
                    @HystrixProperty(name = "maxQueueSize", value = "5"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "80")
            },
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
            }
    )
    public BucketData getBucketsWithCircuit(String customerName) {
        System.out.println("=CIRCUIT= Finding bucket for : " + customerName);
        randomLongResponseTime();
        BucketEntity bucketEntity = findBucketEntity(customerName);
        BucketData bucketData = bucketEntity != null ? BucketMapper.bucketDataMapper(bucketEntity) : null;
        System.out.println("=CIRCUIT= Bucket detail : " + bucketEntity);
        return bucketData;
    }

    private BucketData fallbackGetBucketsWithCircuit(String customerName) {
        System.out.println("xCIRCUITx Starting run fallback");
        return new BucketData(Strings.EMPTY, Collections.emptyList());
    }

    private void randomLongResponseTime() {
        Random random = new Random();
        int num = random.nextInt(3) + 1;
        if (num == 1 || num == 2 || num == 3) sleep();
    }

    private void sleep() {
        System.out.println("=CIRCUIT= sleeping ");
        try {
            Thread.sleep(2100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
