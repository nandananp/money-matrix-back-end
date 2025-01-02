package com.cashcontrol.cashcontrol.service;

import com.cashcontrol.cashcontrol.constants.AdminConstants;
import com.cashcontrol.cashcontrol.constants.UserConstants;
import com.cashcontrol.cashcontrol.entity.admin.Job;
import com.cashcontrol.cashcontrol.entity.admin.Status;
import com.cashcontrol.cashcontrol.entity.admin.Stock;
import com.cashcontrol.cashcontrol.entity.user.User;
import com.cashcontrol.cashcontrol.entity.user.UserGameInfo;
import com.cashcontrol.cashcontrol.entity.user.UserMutualFundInfo;
import com.cashcontrol.cashcontrol.entity.user.UserStockInfo;
import com.cashcontrol.cashcontrol.exception.ResourceNotFoundException;
import com.cashcontrol.cashcontrol.model.response.UserGameInfoDetailResponse;
import com.cashcontrol.cashcontrol.model.response.UserMutualFundResponse;
import com.cashcontrol.cashcontrol.model.response.UserStockResponse;
import com.cashcontrol.cashcontrol.service.repoHandler.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Slf4j
@Service
public class GameStartService {

    @Autowired
    private UserRepoHandler userRepoHandler;
    @Autowired
    private UserGameInfoRepoHandler userGameInfoRepoHandler;
    @Autowired
    private JobRepoHandler jobRepoHandler;
    @Autowired
    private GameDeActivateService gameDeActivateService;
    @Autowired
    UserMutualFundInfoHandler userMutualFundInfoHandler;
    @Autowired
    UserStockInfoRepoHandler userStockInfoRepoHandler;



    public UserGameInfoDetailResponse gameInitiation(UUID userId) {

        UserGameInfo userPreviousGameInfo = userGameInfoRepoHandler.findUserGameInfoByUserIdAndStatus(userId, Status.ACTIVE.toString());
        gameDeActivateService.deActivateUserPreviousGameHistory(userPreviousGameInfo);
        //check any active game present or not
            // if present deactivate the user game and remove history
            // and start a new game (remove all the history from db)


        //pick jobId from job
        List<Job> allJobsAvailable = jobRepoHandler.findAllJobsAvailable();
        if (allJobsAvailable.isEmpty()){
            log.error("Exception:- no job configuration found in admin job configuration table");
            throw new ResourceNotFoundException(AdminConstants.NO_JOB_FOUND);
        }

        //if job is empty throw exception
        Job job = allJobsAvailable.stream().findAny()
                .orElseThrow(()-> new ResourceNotFoundException(AdminConstants.NO_JOB_FOUND));

        UserGameInfo userGameInfo = new UserGameInfo();
        userGameInfo.setUserId(userId);
        userGameInfo.setJobId(job.getId());
        userGameInfo.setJobName(job.getName());
        userGameInfo.setSalary(job.getSalary());
        userGameInfo.setPassiveIncome(0L);
        userGameInfo.setStatus(Status.ACTIVE.name());
        userGameInfoRepoHandler.save(userGameInfo);

        List<UserMutualFundInfo> mutualFunds = userMutualFundInfoHandler.findUserMutualFundsByUserId(userId);
        List<UserStockInfo> userStocks = userStockInfoRepoHandler.findUserStocksByUserId(userId);
        return getUserGameInfoDetailResponse(userGameInfo,mutualFunds,userStocks);

    }


    public UserGameInfoDetailResponse checkGameStatus(User user) {
        UserGameInfo userGameInfo = userGameInfoRepoHandler.findUserGameInfoByUserIdAndStatus(user.getUserId(), Status.ACTIVE.name());
        if (userGameInfo == null){
            log.error("Exception:- no game info found for this uer : {} ",user.getUserId());
            throw new ResourceNotFoundException(UserConstants.USER_GAME_INFO_NOT_FOUND);
        }
        Job job = jobRepoHandler.findById(userGameInfo.getJobId());
        if (job == null){
            throw new ResourceNotFoundException(AdminConstants.NO_JOB_FOUND);
        }
        List<UserMutualFundInfo> mutualFunds = userMutualFundInfoHandler.findUserMutualFundsByUserId(user.getUserId());
        List<UserStockInfo> userStocks = userStockInfoRepoHandler.findUserStocksByUserId(user.getUserId());
        return getUserGameInfoDetailResponse(userGameInfo,mutualFunds,userStocks);


    }

    private static UserGameInfoDetailResponse getUserGameInfoDetailResponse(UserGameInfo userGameInfo,List<UserMutualFundInfo> userMutualFunds, List<UserStockInfo> userStocks) {
        UserGameInfoDetailResponse gameInfoDetailResponse = new UserGameInfoDetailResponse();
        gameInfoDetailResponse.setUserId(userGameInfo.getUserId().toString());
        gameInfoDetailResponse.setJobId(userGameInfo.getJobId().toString());
        gameInfoDetailResponse.setJobName(userGameInfo.getJobName());
        gameInfoDetailResponse.setSalary(userGameInfo.getSalary().toString());
        gameInfoDetailResponse.setPassiveIncome(userGameInfo.getPassiveIncome());
        gameInfoDetailResponse.setMutualFunds(getUserMutualFundResponse(userMutualFunds));
        gameInfoDetailResponse.setStocks(getUserStockResponse(userStocks));
        gameInfoDetailResponse.setGameStatus(userGameInfo.getStatus());
        return gameInfoDetailResponse;
    }

    private static List<UserStockResponse> getUserStockResponse(List<UserStockInfo> userStocks) {
        List<UserStockResponse> userStockResponses = new ArrayList<>();
        if (!userStocks.isEmpty()){
            userStocks.forEach(stocks -> {
                UserStockResponse userStock = new UserStockResponse();
                userStock.setStockId(stocks.getStockId().toString());
                userStock.setStockName(stocks.getStockName());
                userStock.setStockCount(stocks.getStockCount());
                userStock.setInvestedAmount(stocks.getInvestedAmount());
                userStockResponses.add(userStock);
            });
        }
        return userStockResponses;
    }

    private static List<UserMutualFundResponse> getUserMutualFundResponse(List<UserMutualFundInfo> userMutualFunds){
        List<UserMutualFundResponse> userMutualFundResponses = new ArrayList<>();
        if(!userMutualFunds.isEmpty()){
            userMutualFunds.forEach(userMutualFundInfo -> {
                UserMutualFundResponse userMutualFund = new UserMutualFundResponse();
                userMutualFund.setMutualFundId(userMutualFundInfo.getId().toString());
                userMutualFund.setMutualFundName(userMutualFund.getMutualFundName());
                userMutualFund.setTotalReturn(userMutualFundInfo.getTotalReturn());
                userMutualFundResponses.add(userMutualFund);
            });
        }
        return userMutualFundResponses;
    }

}
