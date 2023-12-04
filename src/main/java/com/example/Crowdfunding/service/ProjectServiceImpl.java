package com.example.Crowdfunding.service;

import com.example.Crowdfunding.dao.FundRepository;
import com.example.Crowdfunding.dao.ProjectOptionRepository;
import com.example.Crowdfunding.dao.ProjectRepository;
import com.example.Crowdfunding.dto.ProjectResponse;
import com.example.Crowdfunding.entity.Project;
import com.example.Crowdfunding.entity.ProjectOption;
import com.example.Crowdfunding.exception.ProjectNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {
    private FundRepository fundRepository;
    private ProjectRepository projectRepository;
    private ProjectOptionRepository projectOptionRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public ProjectResponse getProjectById(Long id) {
        Optional<Project> projectOptional = projectRepository.findById(id);
        if (projectOptional.isPresent()){
            Project project = projectOptional.get();
            ProjectResponse response = new ProjectResponse();
            response.setName(project.getName());
            response.setDescription(project.getDescription());
            response.setDaysLeft(DateDifference(project.getDateStart(),project.getDateEnd()));
            response.setStatus(getStatusProject(project));
            return response;
        } else {
            throw new ProjectNotFoundException("Project not found with id: " + id);
        }
    }

    private int getStatusProject(Project project){

        double lowerThreshold = 0.5 * project.getTarget();
        double upperThreshold = 1.0 * project.getTarget();

        double amount = (double) getTotalAmount(project.getId());
        if (amount < lowerThreshold) {
            return 1;
        } else if (amount >= lowerThreshold && amount < upperThreshold) {
            return 2;
        } else {
            return 3;
        }
    }
    private int getTotalAmount(Long id){
        //Optional<List<ProjectOption>> projectOptionsOptional =
        return 0;
    }
    private int DateDifference(Date start, Date end){
        Instant startInstant = start.toInstant();
        Instant endInstant = end.toInstant();

        Duration duration = Duration.between(startInstant, endInstant);

        int days = (int) duration.toDays();

        return days;
    }
}
