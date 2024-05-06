package com.example.jobms.jobpart.repo;


import com.example.jobms.jobpart.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job,Long> {
}
