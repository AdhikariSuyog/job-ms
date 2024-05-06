package com.example.jobms.jobpart.service;
import com.example.jobms.jobpart.dto.JobWithCompanyDto;
import com.example.jobms.jobpart.model.Job;
import org.springframework.http.ResponseEntity;


import java.util.List;

public interface JobService {
    ResponseEntity<List<JobWithCompanyDto>> findAll();

    ResponseEntity<Job> createJob(Job job);

    ResponseEntity<Job> findById(Long id);

    ResponseEntity<Job> updateJob(Job job, Long id);

    ResponseEntity<String> deleteJob(Long id);
}
