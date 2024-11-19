package com.cashcontrol.cashcontrol.service;

import com.cashcontrol.cashcontrol.model.request.*;
import com.cashcontrol.cashcontrol.model.response.LevelInstructionResponse;
import com.cashcontrol.cashcontrol.model.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private MutualFundService mutualFundService;
    @Autowired
    private StockService stockService;
    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private JobService jobService;
    @Autowired
    private LevelInfoService levelInfoService;

    public SuccessResponse createMutualFund(MutualFundRequest mutualFundRequest) {
        return mutualFundService.createMutualFund(mutualFundRequest);
    }

    public SuccessResponse createStock(StockRequest stockRequest) {
        return stockService.createStock(stockRequest);
    }

    public SuccessResponse createExpenseConfiguration(ExpenseRequest expenseRequest) {
        return expenseService.saveExpenseConfiguration(expenseRequest);
    }

    public SuccessResponse createJobConfiguration(JobRequest request) {
        return jobService.saveJobConfiguration(request);
    }

    public List<LevelInstructionResponse> createInstruction(InstructionRequest instructionRequest) {
        return levelInfoService.saveInstruction(instructionRequest);
    }
}
