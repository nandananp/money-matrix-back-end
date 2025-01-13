package com.cashcontrol.cashcontrol.service.repoHandler;

import com.cashcontrol.cashcontrol.constants.AdminConstants;
import com.cashcontrol.cashcontrol.entity.admin.Expense;
import com.cashcontrol.cashcontrol.model.response.SuccessResponse;
import com.cashcontrol.cashcontrol.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseRepoHandler {

    @Autowired
    private ExpenseRepository expenseRepository;

    public SuccessResponse saveExpenseConfiguration(Expense expense) {
        expenseRepository.save(expense);
        return new SuccessResponse(AdminConstants.EXPENSE_ADDED_SUCCESSFULLY);
    }

    public List<Expense> findAllExpenses() {
        return expenseRepository.findAll();
    }
}
