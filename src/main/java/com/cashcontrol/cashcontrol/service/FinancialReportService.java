package com.cashcontrol.cashcontrol.service;

import com.cashcontrol.cashcontrol.entity.admin.Status;
import com.cashcontrol.cashcontrol.entity.admin.Stock;
import com.cashcontrol.cashcontrol.entity.user.UserGameInfo;
import com.cashcontrol.cashcontrol.entity.user.UserLiabilityInfo;
import com.cashcontrol.cashcontrol.entity.user.UserMutualFundInfo;
import com.cashcontrol.cashcontrol.entity.user.UserStockInfo;
import com.cashcontrol.cashcontrol.exception.InvalidRequestException;
import com.cashcontrol.cashcontrol.exception.ResourceNotFoundException;
import com.cashcontrol.cashcontrol.model.response.LevelStatusResponse;
import com.cashcontrol.cashcontrol.model.response.SuccessResponse;
import com.cashcontrol.cashcontrol.service.repoHandler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class FinancialReportService {

    @Autowired
    private UserGameInfoRepoHandler userGameInfoRepoHandler;
    @Autowired
    private UserMutualFundInfoHandler userMutualFundInfoHandler;
    @Autowired
    private UserStockInfoRepoHandler userStockInfoRepoHandler;
    @Autowired
    private UserLiabilityInfoHandler userLiabilityInfoHandler;
    @Autowired
    private StockRepoHandler stockRepoHandler;



    public SuccessResponse reflectOnFinancialStatement(String userId) {

        UserGameInfo userGameInfo = userGameInfoRepoHandler.findUserGameInfoByUserIdAndStatus(UUID.fromString(userId), Status.ACTIVE.name());
        updateTheNextMonthSalary(userGameInfo);
        updateTheLiability(userGameInfo);
        updateTheMutualFundReturn(userGameInfo);
        updateTheStockPrice(userGameInfo);
        return new SuccessResponse("event updated successfully..");
    }



    //update the salary of next month in userGameInfo
    private void updateTheNextMonthSalary(UserGameInfo userGameInfo) {
        Long salary = userGameInfo.getSalary();
        Long savings = userGameInfo.getSavings();
        savings = salary + savings;
        userGameInfo.setSavings(savings);
        userGameInfoRepoHandler.save(userGameInfo);
    }


    private void updateTheMutualFundReturn(UserGameInfo userGameInfo) {
        Long savings = userGameInfo.getSavings();
        List<UserMutualFundInfo> userMutualFunds = userMutualFundInfoHandler.findUserMutualFundsByUserId(userGameInfo.getUserId());
        for (UserMutualFundInfo userMutualFund : userMutualFunds) {
            Long minimumAmount = userMutualFund.getMinimumAmount();
            Long totalReturn = userMutualFund.getTotalReturn();
            totalReturn = (totalReturn + (minimumAmount * 2));
            userMutualFund.setTotalReturn(totalReturn);
            savings = savings - minimumAmount;
        }
        userMutualFundInfoHandler.saveAllMutualFunds(userMutualFunds);
        userGameInfo.setSavings(savings);
        userGameInfoRepoHandler.save(userGameInfo);
    }

    private void updateTheStockPrice(UserGameInfo userGameInfo) {
        List<UserStockInfo> userStocks = userStockInfoRepoHandler.findUserStocksByUserId(userGameInfo.getUserId());
        if (!userStocks.isEmpty()){
            List<UUID> stockIds = userStocks
                    .stream()
                    .map(UserStockInfo::getStockId)
                    .toList();
            List<Stock> stocks = stockRepoHandler.findAllStocksByIds(stockIds);
            for (Stock stock : stocks) {
                Long currentPrice = calculateCurrentPrice(stock.getMinimumPrice(), stock.getMaximumPrice());
                stock.setCurrentPrice(currentPrice);
            }
            stockRepoHandler.saveAllStocks(stocks);
        }

    }

    private Long calculateCurrentPrice(Long minAmount,Long maxAmount){
        Random random = new Random();
        return (Long) (random.nextInt((int) (maxAmount + 1 - minAmount)) + minAmount);
    }


    //update the liability report
    private void updateTheLiability(UserGameInfo userGameInfo) {
        Long savings = userGameInfo.getSavings();
        List<UserLiabilityInfo> userLiabilities = userLiabilityInfoHandler.findUserLiabilityByUserId(userGameInfo.getUserId());
        if (!userLiabilities.isEmpty()){
            for (UserLiabilityInfo userLiability : userLiabilities) {
                Long fullAmount = userLiability.getFullAmount();
                Long emi = userLiability.getEmi();
                fullAmount = fullAmount - emi;
                savings = savings - emi;
                userLiability.setFullAmount(fullAmount);
            }
            userGameInfo.setSavings(savings);
            userLiabilityInfoHandler.saveAllLiabilities(userLiabilities);
            userGameInfoRepoHandler.save(userGameInfo);
        }
    }

    //update financial report based
    public SuccessResponse updateLiabilityDetailsOfUser(String userId,String liabilityId) throws InvalidRequestException {


        UserGameInfo userGameInfo = userGameInfoRepoHandler.findUserGameInfoByUserIdAndStatus(UUID.fromString(userId), Status.ACTIVE.name());


        UserLiabilityInfo userLiabilityInfo = userLiabilityInfoHandler.findByUserIdAndLiabilityId(userId, liabilityId);
        if (userLiabilityInfo == null){
            throw new ResourceNotFoundException("There is no liability found");
        }
        //check savings
        if (userGameInfo.getSavings() < userLiabilityInfo.getFullAmount()){
            throw new InvalidRequestException("you dont have enough money");
        }

        userLiabilityInfoHandler.deleteLiabilityByUserIdAndLiabilityIId(userId,liabilityId);
        Long savings = userGameInfo.getSavings();
        Long fullAmount = userLiabilityInfo.getFullAmount();
        savings = savings - fullAmount;
        userGameInfo.setSavings(savings);

        //find liability
        List<UserLiabilityInfo> userLiabilities = userLiabilityInfoHandler.findUserLiabilityByUserId(userGameInfo.getUserId());
        boolean isLevelOneSatisfied = userLiabilities.isEmpty() && userGameInfo.getLevel() == 0;
        boolean isLevelTwoSatisfied = userLiabilities.isEmpty() && userGameInfo.getLevel() == 1 &&
                userGameInfo.getPassiveIncome() >= userGameInfo.getSalary();
        boolean isLevelThreeSatisField = userLiabilities.isEmpty() && userGameInfo.getLevel() == 2 &&
                userGameInfo.getAssetsCount() >=2;

        if (isLevelOneSatisfied){
            userGameInfo.setLevel(1L);
        }else if (isLevelTwoSatisfied){
            userGameInfo.setLevel(2L);
        } else if (isLevelThreeSatisField) {
            userGameInfo.setLevel(3L);
        }
        userGameInfoRepoHandler.save(userGameInfo);

        //update financial details
      // return reflectOnFinancialStatement(userId);
        return new SuccessResponse("SUCCESS");
    }


    public LevelStatusResponse getLevelFlag(String userId, String levelNumber) {
        UserGameInfo userGameInfo = userGameInfoRepoHandler.findUserGameInfoByUserIdAndStatus(UUID.fromString(userId), Status.ACTIVE.name());
        if (userGameInfo.getLevel().toString().equals(levelNumber) && !userGameInfo.isLevelFlag()){
            userGameInfo.setLevelFlag(true);
            userGameInfoRepoHandler.save(userGameInfo);
            LevelStatusResponse levelStatusResponse = new LevelStatusResponse();
            levelStatusResponse.setLevel(userGameInfo.getLevel());
            levelStatusResponse.setLevelFlag(true);
            return levelStatusResponse;
        }
        LevelStatusResponse levelStatusResponse = new LevelStatusResponse();
        levelStatusResponse.setLevel(userGameInfo.getLevel());
        levelStatusResponse.setLevelFlag(false);
        return levelStatusResponse;

    }
}
