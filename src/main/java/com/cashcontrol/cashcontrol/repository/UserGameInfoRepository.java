package com.cashcontrol.cashcontrol.repository;

import com.cashcontrol.cashcontrol.entity.user.UserGameInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UserGameInfoRepository extends JpaRepository<UserGameInfo, Long> {
    UserGameInfo findByUserIdAndStatus(UUID userId, String status);

}
