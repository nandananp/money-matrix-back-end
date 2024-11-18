package com.cashcontrol.cashcontrol.service;

import com.cashcontrol.cashcontrol.entity.Job;
import com.cashcontrol.cashcontrol.model.request.JobRequest;
import com.cashcontrol.cashcontrol.model.response.SuccessResponse;
import com.cashcontrol.cashcontrol.service.repoHandler.JobRepoHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    @Autowired
    private JobRepoHandler jobRepoHandler;
    public SuccessResponse saveJobConfiguration(JobRequest request) {
        Job job = new Job();
        job.setName(request.getJobName());
        job.setDescription(request.getDescription());
        job.setSalary(request.getSalary());
        return jobRepoHandler.saveJobConfiguration(job);
    }
}
