package com.example.Crowdfunding.dto;


import lombok.Data;

@Data
public class FundResponse {
    private Long id;
    private int amount;
    private String fundLevel;
}
