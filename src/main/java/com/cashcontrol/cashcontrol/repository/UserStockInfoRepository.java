package com.cashcontrol.cashcontrol.repository;

import com.cashcontrol.cashcontrol.entity.user.UserStockInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStockInfoRepository extends JpaRepository<UserStockInfo,Long> {
}
