package com.nonpcn.circuitbreakerdemo.entity;

import lombok.Data;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.LinkedList;
import java.util.List;

@Entity
@Data
public class BucketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName = Strings.EMPTY;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<StuffEntity> stuffEntities = new LinkedList<>();

    protected BucketEntity() {

    }

    public BucketEntity(String customerName) {
        this.customerName = customerName;
        this.stuffEntities = new LinkedList<>();
    }

}
