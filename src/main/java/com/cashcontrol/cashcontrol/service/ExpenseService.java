package com.cashcontrol.cashcontrol.service;

import com.cashcontrol.cashcontrol.entity.Expense;
import com.cashcontrol.cashcontrol.model.request.ExpenseRequest;
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
}
