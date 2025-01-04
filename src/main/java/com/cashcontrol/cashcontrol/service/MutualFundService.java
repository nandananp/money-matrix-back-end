package com.cashcontrol.cashcontrol.service;

import com.cashcontrol.cashcontrol.constants.AdminConstants;
import com.cashcontrol.cashcontrol.entity.admin.MutualFund;
import com.cashcontrol.cashcontrol.model.request.MutualFundRequest;
import com.cashcontrol.cashcontrol.model.response.EventResponse;
import com.cashcontrol.cashcontrol.model.response.SuccessResponse;
import com.cashcontrol.cashcontrol.service.repoHandler.MutualFundRepoHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

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

    private MutualFund randomEventFetcher(){
        List<MutualFund> mutualFunds = mutualFundRepoHandler.findAllMutualFunds();
        Random random = new Random();
        int randomIndex = random.nextInt(mutualFunds.size());
        return mutualFunds.get(randomIndex);
    }

    public EventResponse generateMutualFundEvent() {

        MutualFund mutualFund = randomEventFetcher();
        EventResponse eventResponse = new EventResponse();
        eventResponse.setEventId(mutualFund.getId().toString());
        eventResponse.setEventName(mutualFund.getName());
        eventResponse.setEventType(AdminConstants.EVENT_MUTUAL_FUND);
        eventResponse.setEventDescription(mutualFund.getDescription());
        eventResponse.setEventFixedAmount(mutualFund.getMinimumAmount());
        eventResponse.setEventMandatory(false);
        return eventResponse;


    }
}
