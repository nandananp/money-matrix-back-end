package com.cashcontrol.cashcontrol.service.repoHandler;

import com.cashcontrol.cashcontrol.constants.AdminConstants;
import com.cashcontrol.cashcontrol.entity.admin.Job;
import com.cashcontrol.cashcontrol.model.response.SuccessResponse;
import com.cashcontrol.cashcontrol.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobRepoHandler {

    @Autowired
    private JobRepository jobRepository;

    public SuccessResponse saveJobConfiguration(Job job) {
        jobRepository.save(job);
        return new SuccessResponse(AdminConstants.JOB_REGISTRATION_SUCCESS_RESPONSE);
    }

    public List<Job> findAllJobsAvailable(){
        return jobRepository.findAll();
    }
}
