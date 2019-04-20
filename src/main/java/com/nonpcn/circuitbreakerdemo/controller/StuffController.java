package com.nonpcn.circuitbreakerdemo.controller;

import com.nonpcn.circuitbreakerdemo.model.StuffData;
import com.nonpcn.circuitbreakerdemo.service.StuffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "stuffs")
public class StuffController {

    private StuffService stuffService;

    @Autowired
    public StuffController(StuffService stuffService) {
        this.stuffService = stuffService;
    }

    @GetMapping
    public List<StuffData> getStuffs() {
        return stuffService.getStuffs();
    }

}
