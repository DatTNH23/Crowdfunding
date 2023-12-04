package com.example.Crowdfunding.dao;

import com.example.Crowdfunding.entity.Fund;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FundRepository extends JpaRepository<Fund,Long> {
}
