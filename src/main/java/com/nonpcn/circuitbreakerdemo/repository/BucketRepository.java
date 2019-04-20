package com.nonpcn.circuitbreakerdemo.repository;

import com.nonpcn.circuitbreakerdemo.entity.BucketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BucketRepository extends JpaRepository<BucketEntity, Long> {

    Optional<BucketEntity> findByCustomerName(String customerName);

}
