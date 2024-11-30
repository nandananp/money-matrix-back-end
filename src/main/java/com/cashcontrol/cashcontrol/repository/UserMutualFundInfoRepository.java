package com.cashcontrol.cashcontrol.repository;

import com.cashcontrol.cashcontrol.entity.user.UserMutualFundInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMutualFundInfoRepository extends JpaRepository<UserMutualFundInfo,Long> {
}
