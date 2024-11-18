package com.cashcontrol.cashcontrol.repository;

import com.cashcontrol.cashcontrol.entity.LevelInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelInfoRepository extends JpaRepository<LevelInfo,Long> {

}
