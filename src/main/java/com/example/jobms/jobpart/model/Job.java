package com.example.jobms.jobpart.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;
    private Long companyId;

    public static boolean isEmpty(Job job) {
        return (job.getTitle() == null || job.getTitle().isEmpty())
                || (job.getDescription() == null || job.getDescription().isEmpty())
                || (job.getMinSalary() == null || job.getMinSalary().isEmpty())
                || (job.getMaxSalary() == null || job.getMaxSalary().isEmpty()
                || (job.getLocation() == null || job.getLocation().isEmpty())
                || (job.getCompanyId() == null || job.getCompanyId() == 0));
    }

}
