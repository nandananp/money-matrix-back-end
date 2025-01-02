package com.cashcontrol.cashcontrol.repository;

import com.cashcontrol.cashcontrol.entity.admin.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JobRepository extends JpaRepository<Job,Long> {
    Job findById(UUID jobId);
}
