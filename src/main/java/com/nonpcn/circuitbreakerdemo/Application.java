package com.nonpcn.circuitbreakerdemo;

import com.nonpcn.circuitbreakerdemo.model.BucketData;
import com.nonpcn.circuitbreakerdemo.model.StuffData;
import com.nonpcn.circuitbreakerdemo.service.BucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@SpringBootApplication
@EnableHystrixDashboard
@EnableCircuitBreaker
public class Application {

    @Autowired
    private BucketService bucketService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void initialData() {
        bucketService.createBucket(
                new BucketData("non",
                        Arrays.asList(
                                new StuffData("apple", 15.0, null),
                                new StuffData("donut", 25.0, null),
                                new StuffData("toast", 58.0, null)
                        )
                )
        );
        bucketService.createBucket(
                new BucketData(
                        "adam",
                        Arrays.asList(
                                new StuffData("apple", 15.0, null),
                                new StuffData("cola", 12.0, null)
                        )
                )
        );
        bucketService.createBucket(
                new BucketData(
                        "dene",
                        Arrays.asList()
                )
        );
    }

}
