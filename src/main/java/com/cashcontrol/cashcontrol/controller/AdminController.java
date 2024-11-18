package com.cashcontrol.cashcontrol.controller;

import com.cashcontrol.cashcontrol.model.request.ExpenseRequest;
import com.cashcontrol.cashcontrol.model.request.JobRequest;
import com.cashcontrol.cashcontrol.model.request.MutualFundRequest;
import com.cashcontrol.cashcontrol.model.request.StockRequest;
import com.cashcontrol.cashcontrol.model.response.SuccessResponse;
import com.cashcontrol.cashcontrol.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;


    @PostMapping("/create/mutualFund")
    public SuccessResponse createMutualFund(@RequestBody MutualFundRequest mutualFundRequest){
        return adminService.createMutualFund(mutualFundRequest);
    }

    @PostMapping("/create/stock")
    public SuccessResponse createStock(@RequestBody StockRequest stockRequest){
        return adminService.createStock(stockRequest);
    }

    @PostMapping("/create/expense")
    public SuccessResponse createExpense(@RequestBody ExpenseRequest expenseRequest){
        return adminService.createExpenseConfiguration(expenseRequest);
    }

    @PostMapping("/create/job")
    public SuccessResponse createJob(@RequestBody JobRequest request){
        return adminService.createJobConfiguration(request);
    }


}
