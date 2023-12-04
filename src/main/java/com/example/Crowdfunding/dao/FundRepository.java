package com.example.Crowdfunding.dao;

import com.example.Crowdfunding.entity.Fund;
import com.example.Crowdfunding.entity.ProjectOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FundRepository extends JpaRepository<Fund,Long> {
    Optional<ProjectOption> findProjectOptionById(Long fundId);
    List<Fund> findByProjectId(Long projectId);
}
