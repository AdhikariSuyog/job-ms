package com.example.jobms.jobpart.service;


import com.example.jobms.jobpart.dto.JobWithCompanyDto;
import com.example.jobms.jobpart.exception.InvalidIdException;
import com.example.jobms.jobpart.exception.NothingToUpdateException;
import com.example.jobms.jobpart.external.model.Company;
import com.example.jobms.jobpart.model.Job;
import com.example.jobms.jobpart.repo.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class JobServiceImpl implements JobService {
    @Autowired
    RestTemplate restTemplate;

    private final JobRepository jobRepository;
    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public ResponseEntity<List<JobWithCompanyDto>> findAll() {
        List<Job> jobs = jobRepository.findAll();

        List<JobWithCompanyDto> jobWithCompanyDtos = new ArrayList<>(jobs.stream()
                .map(this::toJobWithCompanyDto)
                .toList());

        return new ResponseEntity<>(jobWithCompanyDtos, HttpStatus.OK);
    }

    private JobWithCompanyDto toJobWithCompanyDto(Job job) {

        return JobWithCompanyDto.builder()
                .job(job)
                .company(restTemplate.getForObject("http://companyms:8080/company/get/" + job.getCompanyId(),
                        Company.class))
                .build();
    }

    @Override
    public ResponseEntity<Job> createJob(Job job) {
        jobRepository.save(job);
        return new ResponseEntity<>(jobRepository.save(job), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Job> findById(Long id) {
        return new ResponseEntity<>(jobRepository.findById(id).orElseThrow(), HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Job> updateJob(Job job, Long id) {
        if (Job.isEmpty(job)) {
            Job jobFromDb = jobRepository.findById(id).orElseThrow(() -> new InvalidIdException("Job with id: " + id + " doesn't exists."));

            Optional.ofNullable(job.getLocation()).map(String::trim)
                    .filter(location -> !location.isEmpty())
                    .ifPresent(jobFromDb::setLocation);
            Optional.ofNullable(job.getMaxSalary()).map(String::trim)
                    .filter(maxSalary -> !maxSalary.isEmpty())
                    .ifPresent(jobFromDb::setMaxSalary);
            Optional.ofNullable(job.getMinSalary()).map(String::trim)
                    .filter(minSalary -> !minSalary.isEmpty())
                    .ifPresent(jobFromDb::setMinSalary);
            Optional.ofNullable(job.getTitle()).map(String::trim)
                    .filter(title -> !title.isEmpty())
                    .ifPresent(jobFromDb::setTitle);
            Optional.ofNullable(job.getDescription()).map(String::trim)
                    .filter(description -> !description.isEmpty())
                    .ifPresent(jobFromDb::setDescription);

            return new ResponseEntity<>(jobRepository.save(jobFromDb), HttpStatus.OK);
        } else
            throw new NothingToUpdateException("There is nothing to update.....");
    }

    @Override
    public ResponseEntity<String> deleteJob(Long id) {
        Optional<Job> optionalJob = jobRepository.findById(id);
        if (optionalJob.isPresent()) {
            Job job = optionalJob.get();
            jobRepository.delete(job);
            return new ResponseEntity<>("job with id: " + id + " is deleted.", HttpStatus.OK);
        }
        return new ResponseEntity<>("job with id: " + id + " doesn't exists.", HttpStatus.BAD_REQUEST);
    }
}
