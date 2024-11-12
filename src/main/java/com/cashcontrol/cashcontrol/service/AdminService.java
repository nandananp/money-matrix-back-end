package com.cashcontrol.cashcontrol.service;

import com.cashcontrol.cashcontrol.model.request.MutualFundRequest;
import com.cashcontrol.cashcontrol.model.request.StockRequest;
import com.cashcontrol.cashcontrol.model.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private MutualFundService mutualFundService;
    @Autowired
    private StockService stockService;

    public SuccessResponse createMutualFund(MutualFundRequest mutualFundRequest) {
        return mutualFundService.createMutualFund(mutualFundRequest);
    }

    public SuccessResponse createStock(StockRequest stockRequest) {
        return stockService.createStock(stockRequest);
    }
}
