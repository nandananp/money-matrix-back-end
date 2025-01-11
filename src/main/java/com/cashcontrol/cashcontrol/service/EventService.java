package com.cashcontrol.cashcontrol.service;

import com.cashcontrol.cashcontrol.entity.admin.Status;
import com.cashcontrol.cashcontrol.entity.user.UserGameInfo;
import com.cashcontrol.cashcontrol.exception.InvalidRequestException;
import com.cashcontrol.cashcontrol.exception.ResourceNotFoundException;
import com.cashcontrol.cashcontrol.model.request.EventRequest;
import com.cashcontrol.cashcontrol.model.response.EventResponse;
import com.cashcontrol.cashcontrol.model.response.SuccessResponse;
import com.cashcontrol.cashcontrol.service.repoHandler.UserGameInfoRepoHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static com.cashcontrol.cashcontrol.constants.AdminConstants.*;

@Service
public class EventService {

    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private MutualFundService mutualFundService;
    @Autowired
    private StockService stockService;
    @Autowired
    private UserGameInfoRepoHandler userGameInfoRepoHandler;



    private List<String> fetchAllEvents(){
        return Arrays.asList(EVENT_CREDIT_CARD,EVENT_DONATION,EVENT_STOCK,EVENT_MUTUAL_FUND,EVENT_WATER_BILL);
    }

    private String randomEventFetcher(){
        List<String> events = fetchAllEvents();
        Random random = new Random();
        int randomIndex = random.nextInt(events.size());
        return events.get(randomIndex);
    }

    public EventResponse nextEvent() throws InvalidRequestException {
        String event = randomEventFetcher();
        switch (event){
            case EVENT_CREDIT_CARD -> {
                return expenseService.generateCreditCardEvent();
            }
            case EVENT_DONATION -> {
                return expenseService.generateDonationEvent();
            }
            case EVENT_WATER_BILL -> {
                return expenseService.generateWaterBillEvent();
            }
            case EVENT_MUTUAL_FUND -> {
                return mutualFundService.generateMutualFundEvent();
            }
            case EVENT_STOCK -> {
                return stockService.generateStockEvent();
            }
            default -> throw new InvalidRequestException("invalid event");
        }
    }

    public SuccessResponse eventDecision(String userId,EventRequest eventRequest) throws InvalidRequestException {
        UserGameInfo userGameInfo = userGameInfoRepoHandler
                .findUserGameInfoByUserIdAndStatus(UUID.fromString(userId), Status.ACTIVE.name());
        if (userGameInfo == null){
            throw new ResourceNotFoundException("User game information not found");
        }
        switch (eventRequest.getEventType()){
            case EVENT_CREDIT_CARD -> {
                return expenseService.creditCardDecision(eventRequest,userGameInfo);
            }
            case EVENT_DONATION -> {
                return expenseService.donationDecision(eventRequest,userGameInfo);
            }
            case EVENT_WATER_BILL -> {
                return expenseService.waterBillDecision(eventRequest,userGameInfo);
            }
            case EVENT_MUTUAL_FUND -> {
                return mutualFundService.mutualFundDecision(eventRequest,userGameInfo);

            }
            case EVENT_STOCK -> {
                return stockService.stockDecision(eventRequest,userGameInfo);
            }

            default -> throw new InvalidRequestException("invalid event type");
        }

        //update the salary of next month in userGameInfo
        //update the stock price if any
        //update the mutual fund return if any

    }
}
