package com.cashcontrol.cashcontrol.service.repoHandler;

import com.cashcontrol.cashcontrol.entity.user.UserMutualFundInfo;
import com.cashcontrol.cashcontrol.repository.UserMutualFundInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserMutualFundInfoHandler {

    @Autowired
    private UserMutualFundInfoRepository userLiabilityInfoRepository;

    public List<UserMutualFundInfo> findUserMutualFundsByUserId(UUID userId) {
        return userLiabilityInfoRepository.findAllByUserId(userId);
    }

    public void deleteAllMutualFundHistoryByUserId(UUID userId) {
        userLiabilityInfoRepository.deleteAllByUserId(userId);
    }

    public void saveUserMutualFundInfo(UserMutualFundInfo userMutualFundInfo) {
        userLiabilityInfoRepository.save(userMutualFundInfo);
    }

    public List<UserMutualFundInfo> findUserMutualFundsByUserIdAndMutualFundId(UUID userId, UUID id) {
        return userLiabilityInfoRepository.findByUserIdAndMutualFundId(userId,id);
    }
}
