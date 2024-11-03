package com.cashcontrol.cashcontrol.repository;

import com.cashcontrol.cashcontrol.entity.MutualFund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MutualFundRepository extends JpaRepository<MutualFund,Long> {
}
