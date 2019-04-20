package com.nonpcn.circuitbreakerdemo;

import com.nonpcn.circuitbreakerdemo.entity.StuffEntity;
import com.nonpcn.circuitbreakerdemo.repository.StuffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class Application {

    @Autowired
    private StuffRepository stuffRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void initialdata() {
        StuffEntity apple = new StuffEntity("apple", 15.0);
        StuffEntity donut = new StuffEntity("donut", 25.0);
        StuffEntity cola = new StuffEntity("cola", 12.0);
        stuffRepository.saveAll(Arrays.asList(apple, donut, cola));
    }

}
