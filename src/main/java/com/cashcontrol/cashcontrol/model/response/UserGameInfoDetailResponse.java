package com.cashcontrol.cashcontrol.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserGameInfoDetailResponse {

    private String userId;
    private String jobId;
    private String jobName;
    private String salary;
    private Long passiveIncome;
    private List<UserMutualFundResponse> mutualFunds;
    private List<UserStockResponse> stocks;

}
