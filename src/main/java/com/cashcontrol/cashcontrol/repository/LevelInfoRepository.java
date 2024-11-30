package com.cashcontrol.cashcontrol.repository;

import com.cashcontrol.cashcontrol.entity.admin.LevelInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LevelInfoRepository extends JpaRepository<LevelInfo,Long> {

    List<LevelInfo> findAllByLevelNumber(Integer integer);

}
