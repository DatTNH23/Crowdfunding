package com.example.Crowdfunding.controller;


import com.example.Crowdfunding.dto.ListFundResponse;
import com.example.Crowdfunding.dto.ProjectResponse;
import com.example.Crowdfunding.service.ProjectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/{id}")
    public ProjectResponse getProjectById(@PathVariable Long id){
        return projectService.getProjectById(id);
    }

    @GetMapping("/totalSum/{id}")
    public int getTotalSum(@PathVariable Long id){
        return projectService.getTotalSumById(id);
    }

    @GetMapping("/funds/{id}")
    public ListFundResponse getFunds(@PathVariable Long id){
        return projectService.getFundsById(id);
    }

}
