package com.cashcontrol.cashcontrol.service.repoHandler;

import com.cashcontrol.cashcontrol.entity.user.UserMutualFundInfo;
import com.cashcontrol.cashcontrol.repository.UserMutualFundInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserMutualFundInfoHandler {

    @Autowired
    private UserMutualFundInfoRepository userMutualFundInfoRepository;

    public List<UserMutualFundInfo> findUserMutualFundsByUserId(UUID userId) {
        return userMutualFundInfoRepository.findAllByUserId(userId);
    }

    public void deleteAllMutualFundHistoryByUserId(UUID userId) {
        userMutualFundInfoRepository.deleteAllByUserId(userId);
    }

    public void saveUserMutualFundInfo(UserMutualFundInfo userMutualFundInfo) {
        userMutualFundInfoRepository.save(userMutualFundInfo);
    }

    public List<UserMutualFundInfo> findUserMutualFundsByUserIdAndMutualFundId(UUID userId, UUID id) {
        return userMutualFundInfoRepository.findByUserIdAndMutualFundId(userId,id);
    }

    public void saveAllMutualFunds(List<UserMutualFundInfo> userMutualFunds) {
        userMutualFundInfoRepository.saveAll(userMutualFunds);
    }
}
