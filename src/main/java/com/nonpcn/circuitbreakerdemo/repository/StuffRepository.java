package com.nonpcn.circuitbreakerdemo.repository;

import com.nonpcn.circuitbreakerdemo.entity.StuffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StuffRepository extends JpaRepository<StuffEntity, Long> {

    Optional<List<StuffEntity>> findByBucketId(Long bucketId);

}
