package com.nonpcn.circuitbreakerdemo.mapper;

import com.nonpcn.circuitbreakerdemo.entity.BucketEntity;
import com.nonpcn.circuitbreakerdemo.model.BucketData;
import org.springframework.stereotype.Component;

@Component
public class BucketMapper {

    public static BucketData bucketDataMapper(BucketEntity bucketEntity) {
        return new BucketData(bucketEntity.getCustomerName(), StuffMapper.stuffDataListMapper(bucketEntity.getStuffEntities()));
    }

}
