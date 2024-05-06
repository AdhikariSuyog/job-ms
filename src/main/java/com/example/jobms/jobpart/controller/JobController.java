package com.example.jobms.jobpart.controller;


import com.example.jobms.jobpart.dto.JobWithCompanyDto;
import com.example.jobms.jobpart.model.Job;
import com.example.jobms.jobpart.service.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job")
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<JobWithCompanyDto>> findAll() {
        return jobService.findAll();
    }

    @GetMapping("/get/{jobId}")
    public ResponseEntity<Job> findById(@PathVariable Long jobId) {
        return jobService.findById(jobId);
    }

    @PostMapping("/post")
    public ResponseEntity<Job> createJob(@RequestBody Job job) {
        return jobService.createJob(job);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Job> updateJob(@RequestBody Job job, @PathVariable Long id) {
        return jobService.updateJob(job, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        return jobService.deleteJob(id);
    }
}
