package com.cashcontrol.cashcontrol.service;

import com.cashcontrol.cashcontrol.entity.admin.Status;
import com.cashcontrol.cashcontrol.entity.user.UserJobInfo;
import com.cashcontrol.cashcontrol.model.response.SuccessResponse;
import com.cashcontrol.cashcontrol.service.repoHandler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GameStartService {

    @Autowired
    private UserRepoHandler userRepoHandler;
    @Autowired
    private UserJobInfoRepoHandler userJobInfoRepoHandler;
    @Autowired
    private JobRepoHandler jobRepoHandler;
    @Autowired
    UserMutualFundInfoHandler userMutualFundInfoHandler;
    @Autowired
    UserStockInfoRepoHandler userStockInfoRepoHandler;


    public SuccessResponse gameInitiation(UUID userId) {

        UserJobInfo userJobInfo = userJobInfoRepoHandler.findUserJobInfoByUserIdAndStatus(userId, Status.ACTIVE.toString());

        //check any active game present or not
            // if present deactivate the user game and remove history
            // and start a new game (remove all the history from db)

        //pick jobId from job
        //if job is empty throw exception

        //save user job details

        return null;



    }
}
