package com.example.Crowdfunding.dao;

import com.example.Crowdfunding.entity.Fund;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundRepository extends JpaRepository<Fund,Long> {
}