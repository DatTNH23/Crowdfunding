package com.example.Crowdfunding.dto;

import lombok.Data;

@Data
public class ProjectResponse {
    private String name;
    private String description;
    private int daysLeft;
    private int status;
}
