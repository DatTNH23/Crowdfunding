package com.example.Crowdfunding.service;

import com.example.Crowdfunding.dao.FundRepository;
import com.example.Crowdfunding.dao.ProjectOptionRepository;
import com.example.Crowdfunding.dao.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {
    private FundRepository fundRepository;
    private ProjectRepository projectRepository;
    private ProjectOptionRepository projectOptionRepository;

    public ProjectServiceImpl(FundRepository fundRepository) {
        this.fundRepository = fundRepository;
    }
}
