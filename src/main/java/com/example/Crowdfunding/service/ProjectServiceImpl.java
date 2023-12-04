package com.example.Crowdfunding.service;

import com.example.Crowdfunding.dao.FundRepository;
import com.example.Crowdfunding.dao.ProjectOptionRepository;
import com.example.Crowdfunding.dao.ProjectRepository;
import com.example.Crowdfunding.dto.FundResponse;
import com.example.Crowdfunding.dto.ListFundResponse;
import com.example.Crowdfunding.dto.ProjectResponse;
import com.example.Crowdfunding.entity.Fund;
import com.example.Crowdfunding.entity.Project;
import com.example.Crowdfunding.entity.ProjectOption;
import com.example.Crowdfunding.exception.NotFoundException;
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

    public ProjectServiceImpl(FundRepository fundRepository, ProjectRepository projectRepository,
                              ProjectOptionRepository projectOptionRepository) {
        this.fundRepository = fundRepository;
        this.projectRepository = projectRepository;
        this.projectOptionRepository = projectOptionRepository;
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
            throw new NotFoundException("Project not found with id: " + id);
        }
    }
    @Override
    public int getTotalSumById(Long id){
        return totalAmount(id);
    }

    @Override
    public ListFundResponse getFundsById(Long id) {
        List<Fund> fundList = getFunds(id);
        ListFundResponse response = new ListFundResponse();
        for (Fund fund : fundList){
            FundResponse fundResponse = new FundResponse();
            fundResponse.setId(fund.getId());
            fundResponse.setAmount(fund.getAmount());
            fundResponse.setFundLevel(getProjectOptionByFundId(fund.getId()).getName());
        }
        return response;
    }
    private ProjectOption getProjectOptionByFundId(Long id){
        Optional<ProjectOption> projectOptionOptional = fundRepository.findProjectOptionById(id);
        if (projectOptionOptional.isPresent()){
            return projectOptionOptional.get();
        }
        else
            throw new NotFoundException("Cannot found ProjectOption");
    }
    private List<Fund> getFunds(Long id){
        List<Fund> fundList = fundRepository.findByProjectId(id);
        if (fundList.isEmpty()) throw new NotFoundException("There are no funds with project id: " + id);
        return fundList;
    }
    private int getStatusProject(Project project){

        double lowerThreshold = 0.5 * project.getTarget();
        double upperThreshold = 1.0 * project.getTarget();

        double amount = (double) getTotalSumById(project.getId());
        if (amount < lowerThreshold) {
            return 1;
        } else if (amount >= lowerThreshold && amount < upperThreshold) {
            return 2;
        } else {
            return 3;
        }
    }
    private int totalAmount(Long id){
        List<Fund> fundList = getFunds(id);
        int sumOfAmount = fundList.stream()
                .mapToInt(Fund::getAmount)
                .sum();
        return sumOfAmount;
    }
    private int DateDifference(Date start, Date end){
        Instant startInstant = start.toInstant();
        Instant endInstant = end.toInstant();

        Duration duration = Duration.between(startInstant, endInstant);

        int days = (int) duration.toDays();

        return days;
    }
}
