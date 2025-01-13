package com.cashcontrol.cashcontrol.service.repoHandler;

import com.cashcontrol.cashcontrol.entity.user.UserGameInfo;
import com.cashcontrol.cashcontrol.repository.UserGameInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class UserGameInfoRepoHandler {

    @Autowired
    private UserGameInfoRepository userGameInfoRepository;

    public UserGameInfo findUserGameInfoByUserIdAndStatus(UUID userId, String status) {
        return userGameInfoRepository.findByUserIdAndStatus(userId,status);
    }

    public void save(UserGameInfo userGameInfo) {
         userGameInfoRepository.save(userGameInfo);
    }
}
