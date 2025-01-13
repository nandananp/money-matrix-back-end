package com.cashcontrol.cashcontrol.service.repoHandler;

import com.cashcontrol.cashcontrol.entity.user.UserLiabilityInfo;
import com.cashcontrol.cashcontrol.repository.UserLiabilityInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserLiabilityInfoHandler {

    @Autowired
    private UserLiabilityInfoRepository userLiabilityInfoRepository;

    public List<UserLiabilityInfo> findUserLiabilityByUserId(UUID userId) {
        return userLiabilityInfoRepository.findAllByUserId(userId);
    }

    public void deleteAllLiabilityHistoryByUserId(UUID userId) {
        userLiabilityInfoRepository.deleteAllByUserId(userId);
    }

    public void saveAllLiabilities(List<UserLiabilityInfo> userLiabilities) {
        userLiabilityInfoRepository.saveAll(userLiabilities);
    }
}
