package com.cashcontrol.cashcontrol.service;

import com.cashcontrol.cashcontrol.constants.AdminConstants;
import com.cashcontrol.cashcontrol.constants.UserConstants;
import com.cashcontrol.cashcontrol.entity.admin.Stock;
import com.cashcontrol.cashcontrol.entity.user.UserGameInfo;
import com.cashcontrol.cashcontrol.entity.user.UserStockInfo;
import com.cashcontrol.cashcontrol.exception.InvalidRequestException;
import com.cashcontrol.cashcontrol.model.request.EventRequest;
import com.cashcontrol.cashcontrol.model.request.StockRequest;
import com.cashcontrol.cashcontrol.model.response.EventResponse;
import com.cashcontrol.cashcontrol.model.response.SuccessResponse;
import com.cashcontrol.cashcontrol.service.repoHandler.StockRepoHandler;
import com.cashcontrol.cashcontrol.service.repoHandler.UserGameInfoRepoHandler;
import com.cashcontrol.cashcontrol.service.repoHandler.UserStockInfoRepoHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class StockService {

    @Autowired
    private StockRepoHandler stockRepoHandler;
    @Autowired
    private UserStockInfoRepoHandler userStockInfoRepoHandler;
    @Autowired
    private UserGameInfoRepoHandler userGameInfoRepoHandler;

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

    public SuccessResponse stockDecision(EventRequest eventRequest, UserGameInfo userGameInfo) throws InvalidRequestException {
        Stock stock = stockRepoHandler.findStockByStockId(eventRequest.getEventId());
        if (stock == null){
            throw new InvalidRequestException("Stock not found with this event id");
        }
        UserStockInfo userStockInfo = userStockInfoRepoHandler.findUserStocksByUserIdAndStockId(userGameInfo.getUserId(),stock.getId());
        if (eventRequest.getEventType().equals(UserConstants.EVENT_DECISION_SELL)){
            if (userStockInfo == null){
                throw new InvalidRequestException("currently you don have this stock in you Asset column..!");
            }else {
                Long stockCount = userStockInfo.getStockCount();
                Long currentPrice = userStockInfo.getCurrentPrice();
                Long investedAmount = userStockInfo.getInvestedAmount();
                Long savings = userGameInfo.getSavings();
                savings = savings + currentPrice;
                userGameInfo.setSavings(savings);
                userGameInfoRepoHandler.save(userGameInfo);
                userStockInfoRepoHandler.deleteStockByUserIdAndStockId(userStockInfo.getUserId(),userStockInfo.getStockId());
                return new SuccessResponse("event updated successfully");
            }
        }else if (eventRequest.getEventType().equals(UserConstants.EVENT_DECISION_ACCEPT) ||
        eventRequest.getEventType().equals(UserConstants.EVENT_DECISION_BUY)){
            if (userStockInfo != null){
                throw new InvalidRequestException("currently you can't buy same stock twice..");
            }else {
                UserStockInfo userNewStock = new UserStockInfo();

                userNewStock.setStockId(stock.getId());
                userNewStock.setStockName(stock.getName());
                userNewStock.setUserId(userGameInfo.getUserId());
                userNewStock.setCurrentPrice(stock.getCurrentPrice());
                userNewStock.setStockCount(eventRequest.getEventCount());

                Long savings = userGameInfo.getSavings();
                Long currentPrice = stock.getCurrentPrice();
                Long investedAmount = currentPrice* eventRequest.getEventCount();

                userNewStock.setInvestedAmount(investedAmount);
                userStockInfoRepoHandler.saveUserStockInfo(userNewStock);
                long savingsBalance = savings - investedAmount;
                userGameInfo.setSavings(savingsBalance);
                userGameInfoRepoHandler.save(userGameInfo);
                return new SuccessResponse("event updated successfully");
            }
        }
        return new SuccessResponse("event updated successfully");
    }
}
