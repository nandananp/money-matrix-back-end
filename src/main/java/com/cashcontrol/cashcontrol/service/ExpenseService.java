package com.cashcontrol.cashcontrol.service;

import com.cashcontrol.cashcontrol.constants.AdminConstants;
import com.cashcontrol.cashcontrol.constants.UserConstants;
import com.cashcontrol.cashcontrol.entity.admin.Expense;
import com.cashcontrol.cashcontrol.model.request.ExpenseRequest;
import com.cashcontrol.cashcontrol.model.response.EventResponse;
import com.cashcontrol.cashcontrol.model.response.SuccessResponse;
import com.cashcontrol.cashcontrol.service.repoHandler.ExpenseRepoHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepoHandler expenseRepoHandler;


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
        eventResponse.setEventFixedAmount(1000L);
        eventResponse.setEventMandatory(true);
        return eventResponse;

    }

    public EventResponse generateDonationEvent() {
        EventResponse eventResponse = new EventResponse();
        eventResponse.setEventId(UserConstants.DONATION_ID);
        eventResponse.setEventType(AdminConstants.EVENT_DONATION);
        eventResponse.setEventName("Smile Foundation");
        eventResponse.setEventDescription("Donate amount to charity..");
        eventResponse.setEventFixedAmount(100L);
        eventResponse.setEventMandatory(false);
        return eventResponse;
    }

    public EventResponse generateWaterBillEvent() {
        EventResponse eventResponse = new EventResponse();
        eventResponse.setEventId(UserConstants.WATER_BILL_ID);
        eventResponse.setEventType(AdminConstants.EVENT_WATER_BILL);
        eventResponse.setEventName("KERALA WATER AUTHORITY");
        eventResponse.setEventDescription("Pay your water bill..");
        eventResponse.setEventFixedAmount(500L);
        eventResponse.setEventMandatory(true);
        return eventResponse;
    }
}
