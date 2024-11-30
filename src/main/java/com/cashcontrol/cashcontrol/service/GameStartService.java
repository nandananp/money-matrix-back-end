package com.cashcontrol.cashcontrol.service;

import com.cashcontrol.cashcontrol.constants.AdminConstants;
import com.cashcontrol.cashcontrol.entity.admin.Job;
import com.cashcontrol.cashcontrol.entity.admin.Status;
import com.cashcontrol.cashcontrol.entity.user.UserGameInfo;
import com.cashcontrol.cashcontrol.exception.ResourceNotFoundException;
import com.cashcontrol.cashcontrol.model.response.SuccessResponse;
import com.cashcontrol.cashcontrol.model.response.UserGameInfoDetailResponse;
import com.cashcontrol.cashcontrol.service.repoHandler.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
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



    public UserGameInfoDetailResponse gameInitiation(UUID userId) {

        UserGameInfo userPreviousGameInfo = userGameInfoRepoHandler.findUserJobInfoByUserIdAndStatus(userId, Status.ACTIVE.toString());
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

        UserGameInfoDetailResponse gameInfoDetailResponse = new UserGameInfoDetailResponse();
        gameInfoDetailResponse.setUserId(userId.toString());
        gameInfoDetailResponse.setJobId(job.getId().toString());
        gameInfoDetailResponse.setJobName(job.getName());
        gameInfoDetailResponse.setSalary(job.getSalary().toString());
        gameInfoDetailResponse.setPassiveIncome(0L);
        gameInfoDetailResponse.setMutualFunds(new ArrayList<>());
        gameInfoDetailResponse.setStocks(new ArrayList<>());

        return gameInfoDetailResponse;

    }
}
