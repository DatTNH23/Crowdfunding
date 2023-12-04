package com.example.Crowdfunding.dao;

import com.example.Crowdfunding.entity.ProjectOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectOptionRepository extends JpaRepository<ProjectOption, Long> {
    Optional<List<ProjectOption>> findByProjectId(Long projectId);
}
