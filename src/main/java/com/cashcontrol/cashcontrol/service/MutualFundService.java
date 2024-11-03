package com.cashcontrol.cashcontrol.service;

import com.cashcontrol.cashcontrol.entity.MutualFund;
import com.cashcontrol.cashcontrol.model.request.MutualFundRequest;
import com.cashcontrol.cashcontrol.model.response.SuccessResponse;
import com.cashcontrol.cashcontrol.service.repoHandler.MutualFundRepoHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MutualFundService {

    @Autowired
    private MutualFundRepoHandler mutualFundRepoHandler;



    //create mutual fund by admin
    public SuccessResponse createMutualFund(MutualFundRequest mutualFundRequest) {

        MutualFund mutualFund = new MutualFund();

        mutualFund.setName(mutualFundRequest.getMutualFundName());
        mutualFund.setDescription(mutualFundRequest.getDescription());
        mutualFund.setMinimumAmount(mutualFundRequest.getMinimumAmount());

        return mutualFundRepoHandler.saveMutualFund(mutualFund);

    }
}
