package com.example.Crowdfunding.service;

import com.example.Crowdfunding.dto.ListFundResponse;
import com.example.Crowdfunding.dto.ProjectResponse;

public interface ProjectService {

    ProjectResponse getProjectById(Long id);
    int getTotalSumById(Long id);

    ListFundResponse getFundsById(Long id);

}
