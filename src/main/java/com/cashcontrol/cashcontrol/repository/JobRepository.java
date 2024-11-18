package com.cashcontrol.cashcontrol.repository;

import com.cashcontrol.cashcontrol.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job,Long> {
}
