package com.cashcontrol.cashcontrol.repository;

import com.cashcontrol.cashcontrol.entity.user.UserMutualFundInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserMutualFundInfoRepository extends JpaRepository<UserMutualFundInfo,Long> {
    List<UserMutualFundInfo> findAllByUserId(UUID userId);

    void deleteAllByUserId(UUID userId);
}
