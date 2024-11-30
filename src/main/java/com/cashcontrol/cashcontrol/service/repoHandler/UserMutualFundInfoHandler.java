package com.cashcontrol.cashcontrol.service.repoHandler;

import com.cashcontrol.cashcontrol.entity.user.UserMutualFundInfo;
import com.cashcontrol.cashcontrol.repository.UserMutualFundInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserMutualFundInfoHandler {

    @Autowired
    private UserMutualFundInfoRepository userMutualFundInfoRepository;

    public List<UserMutualFundInfo> findUserMutualFundsByUserId(UUID userId) {
        return userMutualFundInfoRepository.findAllByUserId(userId);
    }

    public void deleteAllMutualFundHistoryByUserId(UUID userId) {
        userMutualFundInfoRepository.deleteAllByUserId(userId);
    }
}
