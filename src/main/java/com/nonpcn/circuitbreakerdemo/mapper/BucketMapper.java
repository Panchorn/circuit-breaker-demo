package com.nonpcn.circuitbreakerdemo.mapper;

import com.nonpcn.circuitbreakerdemo.entity.BucketEntity;
import com.nonpcn.circuitbreakerdemo.model.BucketData;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BucketMapper {

    public static BucketData bucketDataMapper(BucketEntity bucketEntity) {
        return new BucketData(bucketEntity.getCustomerName(), StuffMapper.stuffDataListMapper(bucketEntity.getStuffs()));
    }

    public static List<BucketData> bucketDataListMapper(List<BucketEntity> bucketEntityList) {
        return bucketEntityList.stream().map(bucketEntity -> bucketDataMapper(bucketEntity)).collect(Collectors.toList());
    }

}
