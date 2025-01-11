package com.cashcontrol.cashcontrol.service;

import com.cashcontrol.cashcontrol.constants.AdminConstants;
import com.cashcontrol.cashcontrol.constants.UserConstants;
import com.cashcontrol.cashcontrol.entity.admin.MutualFund;
import com.cashcontrol.cashcontrol.entity.user.UserGameInfo;
import com.cashcontrol.cashcontrol.entity.user.UserMutualFundInfo;
import com.cashcontrol.cashcontrol.exception.InvalidRequestException;
import com.cashcontrol.cashcontrol.model.request.EventRequest;
import com.cashcontrol.cashcontrol.model.request.MutualFundRequest;
import com.cashcontrol.cashcontrol.model.response.EventResponse;
import com.cashcontrol.cashcontrol.model.response.SuccessResponse;
import com.cashcontrol.cashcontrol.service.repoHandler.MutualFundRepoHandler;
import com.cashcontrol.cashcontrol.service.repoHandler.UserGameInfoRepoHandler;
import com.cashcontrol.cashcontrol.service.repoHandler.UserMutualFundInfoHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class MutualFundService {

    @Autowired
    private MutualFundRepoHandler mutualFundRepoHandler;
    @Autowired
    private UserMutualFundInfoHandler userMutualFundInfoHandler;
    @Autowired
    private UserGameInfoRepoHandler userGameInfoRepoHandler;



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

    public SuccessResponse mutualFundDecision(EventRequest eventRequest, UserGameInfo userGameInfo) throws InvalidRequestException {

        if (eventRequest.getEventType().equals(UserConstants.EVENT_DECISION_ACCEPT) ||
                eventRequest.getEventType().equals(UserConstants.EVENT_DECISION_BUY)){
            MutualFund mutualFund =  mutualFundRepoHandler.findByMutualFundId(eventRequest.getEventId());
            if (mutualFund == null){
                throw new InvalidRequestException("MutualFund not found with this event id");
            }
            List<UserMutualFundInfo> existingUserMutualFund = userMutualFundInfoHandler.findUserMutualFundsByUserIdAndMutualFundId(userGameInfo.getUserId(),mutualFund.getId());
            if (existingUserMutualFund != null){
                throw new InvalidRequestException("currently you can't start same mutual fund..");
            }
            UserMutualFundInfo userMutualFundInfo = new UserMutualFundInfo();
            userMutualFundInfo.setUserId(userGameInfo.getUserId());
            userMutualFundInfo.setMutualFundId(mutualFund.getId());
            userMutualFundInfo.setTotalReturn(mutualFund.getMinimumAmount());
            userMutualFundInfoHandler.saveUserMutualFundInfo(userMutualFundInfo);
            Long savings = userGameInfo.getSavings();
            Long savingBalance = savings - mutualFund.getMinimumAmount();
            userGameInfo.setSavings(savingBalance);
            userGameInfoRepoHandler.save(userGameInfo);

        }
        return new SuccessResponse("event updated successfully");

    }
}
