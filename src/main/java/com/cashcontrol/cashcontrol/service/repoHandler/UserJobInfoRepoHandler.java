package com.cashcontrol.cashcontrol.service.repoHandler;

import com.cashcontrol.cashcontrol.entity.UserJobInfo;
import com.cashcontrol.cashcontrol.repository.UserJobInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserJobInfoRepoHandler {

    @Autowired
    private UserJobInfoRepository userJobInfoRepository;

    public UserJobInfo findUserJobInfoByUserIdAndStatus(UUID userId,String status) {
        return userJobInfoRepository.findByUserIdAndStatus(userId,status);
    }
}
