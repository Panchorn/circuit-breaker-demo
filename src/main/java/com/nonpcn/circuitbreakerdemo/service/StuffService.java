package com.nonpcn.circuitbreakerdemo.service;

import com.nonpcn.circuitbreakerdemo.entity.StuffEntity;
import com.nonpcn.circuitbreakerdemo.mapper.StuffMapper;
import com.nonpcn.circuitbreakerdemo.model.StuffData;
import com.nonpcn.circuitbreakerdemo.repository.StuffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StuffService {

    private StuffRepository stuffRepository;

    @Autowired
    public StuffService(StuffRepository stuffRepository) {
        this.stuffRepository = stuffRepository;
    }

    public List<StuffData> getStuffs() {
        System.out.println("Finding all stuff");
        List<StuffEntity> stuffEntityList = stuffRepository.findAll();
        List<StuffData> stuffDataList = StuffMapper.stuffDataListMapper(stuffEntityList);
        System.out.println("All stuff\n" + stuffEntityList);
        return stuffDataList;
    }

}
