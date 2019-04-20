package com.nonpcn.circuitbreakerdemo.mapper;

import com.nonpcn.circuitbreakerdemo.entity.StuffEntity;
import com.nonpcn.circuitbreakerdemo.model.StuffData;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StuffMapper {

    public static List<StuffData> stuffDataListMapper(List<StuffEntity> stuffEntityList) {
        return stuffEntityList.stream().map(stuffEntity -> stuffDataMapper(stuffEntity)).collect(Collectors.toList());
    }

    public static StuffData stuffDataMapper(StuffEntity stuffEntity) {
        return new StuffData(stuffEntity.getName(), stuffEntity.getPrice());
    }

    public static List<StuffEntity> stuffEntityListMapper(List<StuffData> stuffDataList) {
        return stuffDataList.stream().map(stuffData -> stuffEntityMapper(stuffData)).collect(Collectors.toList());
    }

    public static StuffEntity stuffEntityMapper(StuffData stuffData) {
        return new StuffEntity(stuffData.getName(), stuffData.getPrice());
    }

}
