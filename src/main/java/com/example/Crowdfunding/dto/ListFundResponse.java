package com.example.Crowdfunding.dto;

import com.example.Crowdfunding.entity.Fund;
import lombok.Data;

import java.util.List;

@Data
public class ListFundResponse {
    List<FundResponse> listFund;
}
