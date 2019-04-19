package com.nonpcn.circuitbreakerdemo.repository;

import com.nonpcn.circuitbreakerdemo.entity.BucketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BucketRepository extends JpaRepository<BucketEntity, Long> {
}
