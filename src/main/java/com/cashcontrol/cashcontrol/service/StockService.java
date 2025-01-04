package com.cashcontrol.cashcontrol.service;

import com.cashcontrol.cashcontrol.constants.AdminConstants;
import com.cashcontrol.cashcontrol.entity.admin.MutualFund;
import com.cashcontrol.cashcontrol.entity.admin.Stock;
import com.cashcontrol.cashcontrol.model.request.StockRequest;
import com.cashcontrol.cashcontrol.model.response.EventResponse;
import com.cashcontrol.cashcontrol.model.response.SuccessResponse;
import com.cashcontrol.cashcontrol.service.repoHandler.StockRepoHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

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

    private Stock randomEventFetcher(){
        List<Stock> stocks = stockRepoHandler.findAllStocks();
        Random random = new Random();
        int randomIndex = random.nextInt(stocks.size());
        return stocks.get(randomIndex);
    }

    public EventResponse generateStockEvent() {
        Stock stock = randomEventFetcher();
        EventResponse eventResponse = new EventResponse();
        eventResponse.setEventId(stock.getId().toString());
        eventResponse.setEventName(stock.getName());
        eventResponse.setEventType(AdminConstants.EVENT_STOCK);
        eventResponse.setEventDescription(stock.getDescription());
        eventResponse.setEventMinimumAmount(stock.getMinimumPrice());
        eventResponse.setEventMaximumAmount(stock.getMaximumPrice());
        eventResponse.setEventCurrentPrice(calculateCurrentPrice(stock.getMinimumPrice(),stock.getMaximumPrice()));
        eventResponse.setEventMandatory(false);
        return eventResponse;
    }

    private Long calculateCurrentPrice(Long minAmount,Long maxAmount){
        Random random = new Random();
        return (Long) (random.nextInt((int) (maxAmount + 1 - minAmount)) + minAmount);
    }
}
