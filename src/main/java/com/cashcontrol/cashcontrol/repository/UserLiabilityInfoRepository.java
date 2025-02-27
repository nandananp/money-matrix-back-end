package com.cashcontrol.cashcontrol.repository;

import com.cashcontrol.cashcontrol.entity.user.UserLiabilityInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserLiabilityInfoRepository extends JpaRepository<UserLiabilityInfo,Long> {
    List<UserLiabilityInfo> findAllByUserId(UUID userId);

    void deleteAllByUserId(UUID userId);

    void deleteAllByUserIdAndLiabilityId(UUID uuid, UUID uuid1);

    UserLiabilityInfo findByUserIdAndLiabilityId(UUID uuid, UUID uuid1);
}
