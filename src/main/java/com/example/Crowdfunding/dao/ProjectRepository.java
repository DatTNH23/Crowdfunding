package com.example.Crowdfunding.dao;

import com.example.Crowdfunding.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
