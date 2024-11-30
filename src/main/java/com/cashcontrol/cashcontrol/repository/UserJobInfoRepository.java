package com.cashcontrol.cashcontrol.repository;

import com.cashcontrol.cashcontrol.entity.user.UserJobInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface UserJobInfoRepository extends JpaRepository<UserJobInfo, Long> {
    UserJobInfo findByUserIdAndStatus(UUID userId,String status);

}
