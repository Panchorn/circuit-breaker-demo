package com.nonpcn.circuitbreakerdemo.entity;

import lombok.Data;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.LinkedList;
import java.util.List;

@Entity
@Data
public class BucketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bucketId;
    private String customerName = Strings.EMPTY;

    @Transient
    private List<StuffEntity> stuffs = new LinkedList<>();

    protected BucketEntity() {

    }

    public BucketEntity(String customerName) {
        this.customerName = customerName;
        this.stuffs = new LinkedList<>();
    }

}
