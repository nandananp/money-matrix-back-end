package com.cashcontrol.cashcontrol.repository;

import com.cashcontrol.cashcontrol.entity.UserJobInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UserJobInfoRepository extends JpaRepository<UserJobInfo, UUID> {
    UserJobInfo findByUserIdAndStatus(UUID userId,String status);

}
