package com.cashcontrol.cashcontrol.service;

import com.cashcontrol.cashcontrol.entity.admin.Status;
import com.cashcontrol.cashcontrol.entity.user.UserGameInfo;
import com.cashcontrol.cashcontrol.service.repoHandler.UserGameInfoRepoHandler;
import com.cashcontrol.cashcontrol.service.repoHandler.UserLiabilityInfoHandler;
import com.cashcontrol.cashcontrol.service.repoHandler.UserMutualFundInfoHandler;
import com.cashcontrol.cashcontrol.service.repoHandler.UserStockInfoRepoHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameDeActivateService {

    @Autowired
    UserMutualFundInfoHandler userMutualFundInfoHandler;
    @Autowired
    UserStockInfoRepoHandler userStockInfoRepoHandler;
    @Autowired
    private UserGameInfoRepoHandler userGameInfoRepoHandler;
    @Autowired
    private UserLiabilityInfoHandler userLiabilityInfoHandler;

    public void deActivateUserPreviousGameHistory(UserGameInfo userGameInfo) {
        if (userGameInfo != null){
            userGameInfo.setStatus(Status.INACTIVE.name());

            //deActivate userJobInfo from main table
            userGameInfoRepoHandler.save(userGameInfo);

            //clear all previous M.F history
            userMutualFundInfoHandler.deleteAllMutualFundHistoryByUserId(userGameInfo.getUserId());

            //clear all previous stock history
            userStockInfoRepoHandler.deleteAllStockHistoryByUserId(userGameInfo.getUserId());

            //clear all previous liability history
            userLiabilityInfoHandler.deleteAllLiabilityHistoryByUserId(userGameInfo.getUserId());
        }
    }
}
