package com.cashcontrol.cashcontrol.service;

import com.cashcontrol.cashcontrol.exception.InvalidRequestException;
import com.cashcontrol.cashcontrol.model.response.EventResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.cashcontrol.cashcontrol.constants.AdminConstants.*;

@Service
public class EventService {

    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private MutualFundService mutualFundService;
    @Autowired
    private StockService stockService;


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
}
