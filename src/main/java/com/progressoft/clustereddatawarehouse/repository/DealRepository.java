package com.progressoft.clustereddatawarehouse.repository;

import com.progressoft.clustereddatawarehouse.model.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealRepository extends JpaRepository<Deal, String> {
}
