package com.nonpcn.circuitbreakerdemo.repository;

import com.nonpcn.circuitbreakerdemo.entity.StuffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StuffRepository extends JpaRepository<StuffEntity, Long> {
}
