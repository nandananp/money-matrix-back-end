package com.cashcontrol.cashcontrol.service.repoHandler;

import com.cashcontrol.cashcontrol.entity.admin.Stock;
import com.cashcontrol.cashcontrol.entity.user.UserStockInfo;
import com.cashcontrol.cashcontrol.repository.UserStockInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserStockInfoRepoHandler {

    @Autowired
    private UserStockInfoRepository userStockInfoRepository;


    public void deleteAllStockHistoryByUserId(UUID userId) {
        userStockInfoRepository.deleteAllByUserId(userId);
    }

    public List<UserStockInfo> findUserStocksByUserId(UUID userId) {
        return userStockInfoRepository.findAllByUserId(userId);
    }

    public UserStockInfo findUserStocksByUserIdAndStockId(UUID userId, UUID id) {
        return userStockInfoRepository.findByUserIdAndStockId(userId,id);
    }

    public void deleteStockByUserIdAndStockId(UUID userId, UUID stockId) {
        userStockInfoRepository.deleteByUserIdAndStockId(userId,stockId);
    }

    public void saveUserStockInfo(UserStockInfo userNewStock) {
        userStockInfoRepository.save(userNewStock);
    }
}
