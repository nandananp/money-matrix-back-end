package com.cashcontrol.cashcontrol.service;

import com.cashcontrol.cashcontrol.constants.AdminConstants;
import com.cashcontrol.cashcontrol.constants.UserConstants;
import com.cashcontrol.cashcontrol.entity.admin.Expense;
import com.cashcontrol.cashcontrol.entity.user.UserGameInfo;
import com.cashcontrol.cashcontrol.exception.InvalidRequestException;
import com.cashcontrol.cashcontrol.model.request.EventRequest;
import com.cashcontrol.cashcontrol.model.request.ExpenseRequest;
import com.cashcontrol.cashcontrol.model.response.EventResponse;
import com.cashcontrol.cashcontrol.model.response.SuccessResponse;
import com.cashcontrol.cashcontrol.service.repoHandler.ExpenseRepoHandler;
import com.cashcontrol.cashcontrol.service.repoHandler.UserGameInfoRepoHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.cashcontrol.cashcontrol.constants.UserConstants.*;

@Service
public class ExpenseService {



    @Autowired
    private ExpenseRepoHandler expenseRepoHandler;
    @Autowired
    private UserGameInfoRepoHandler userGameInfoRepoHandler;


    public SuccessResponse saveExpenseConfiguration(ExpenseRequest expenseRequest) {
        Expense expense = new Expense();
        expense.setName(expenseRequest.getExpenseName());
        expense.setDescription(expenseRequest.getDescription());
        expense.setFullAmount(expenseRequest.getFullAmount());
        expense.setEmi(expenseRequest.getEmi());
        return expenseRepoHandler.saveExpenseConfiguration(expense);
    }

    public EventResponse generateCreditCardEvent() {
        EventResponse eventResponse = new EventResponse();
        eventResponse.setEventId(UserConstants.CREDIT_CARD_ID);
        eventResponse.setEventType(AdminConstants.EVENT_CREDIT_CARD);
        eventResponse.setEventName("HDFC CREDIT CARD");
        eventResponse.setEventDescription("Pay your credit card bill!!!");
        eventResponse.setEventFixedAmount(CREDIT_CARD_AMOUNT);
        eventResponse.setEventMandatory(true);
        return eventResponse;

    }

    public EventResponse generateDonationEvent() {
        EventResponse eventResponse = new EventResponse();
        eventResponse.setEventId(UserConstants.DONATION_ID);
        eventResponse.setEventType(AdminConstants.EVENT_DONATION);
        eventResponse.setEventName("Smile Foundation");
        eventResponse.setEventDescription("Donate amount to charity..");
        eventResponse.setEventFixedAmount(DONATION_AMOUNT);
        eventResponse.setEventMandatory(false);
        return eventResponse;
    }

    public EventResponse generateWaterBillEvent() {
        EventResponse eventResponse = new EventResponse();
        eventResponse.setEventId(UserConstants.WATER_BILL_ID);
        eventResponse.setEventType(AdminConstants.EVENT_WATER_BILL);
        eventResponse.setEventName("KERALA WATER AUTHORITY");
        eventResponse.setEventDescription("Pay your water bill..");
        eventResponse.setEventFixedAmount(WATER_BILL);
        eventResponse.setEventMandatory(true);
        return eventResponse;
    }

    public SuccessResponse creditCardDecision(EventRequest eventRequest,UserGameInfo userGameInfo) throws InvalidRequestException {
        if (!eventRequest.getEventId().equals(CREDIT_CARD_ID)){
            throw new InvalidRequestException("invalid credit card id input as event id");
        }
        Long currentSavings = userGameInfo.getSavings();
        long savingsBalance = currentSavings - CREDIT_CARD_AMOUNT;
        userGameInfo.setSavings(savingsBalance);
        userGameInfoRepoHandler.save(userGameInfo);
        return new SuccessResponse("event updated successfully");
    }

    public SuccessResponse donationDecision(EventRequest eventRequest, UserGameInfo userGameInfo) throws InvalidRequestException {
        if (!eventRequest.getEventId().equals(DONATION_ID)){
            throw new InvalidRequestException("invalid donation id input as event id");
        }
        if (eventRequest.getEventDecision().equals(EVENT_DECISION_ACCEPT)){
            Long savings = userGameInfo.getSavings();
            long savingsBalance = savings - DONATION_AMOUNT;
            userGameInfo.setSavings(savingsBalance);
            userGameInfoRepoHandler.save(userGameInfo);
        }
        return new SuccessResponse("event updated successfully");
    }

    public SuccessResponse waterBillDecision(EventRequest eventRequest, UserGameInfo userGameInfo) throws InvalidRequestException {
        if (!eventRequest.getEventId().equals(WATER_BILL_ID)){
            throw new InvalidRequestException("invalid water bill id input as event id");
        }
            Long savings = userGameInfo.getSavings();
            long savingsBalance = savings - WATER_BILL;
            userGameInfo.setSavings(savingsBalance);
            userGameInfoRepoHandler.save(userGameInfo);
        return new SuccessResponse("event updated successfully");
    }
}
