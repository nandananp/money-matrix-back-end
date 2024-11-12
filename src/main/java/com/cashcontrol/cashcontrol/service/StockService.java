package com.cashcontrol.cashcontrol.service;

import com.cashcontrol.cashcontrol.entity.Stock;
import com.cashcontrol.cashcontrol.model.request.StockRequest;
import com.cashcontrol.cashcontrol.model.response.SuccessResponse;
import com.cashcontrol.cashcontrol.service.repoHandler.StockRepoHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    @Autowired
    private StockRepoHandler stockRepoHandler;

    public SuccessResponse createStock(StockRequest stockRequest) {
        Stock stock = new Stock();
        stock.setName(stockRequest.getStockName());
        stock.setDescription(stockRequest.getDescription());
        stock.setMinimumPrice(stockRequest.getMinPrice());
        stock.setMaximumPrice(stockRequest.getMaxPrice());
        stock.setCurrentPrice(stockRequest.getCurrentPrice());
        return stockRepoHandler.saveStock(stock);

    }
}
