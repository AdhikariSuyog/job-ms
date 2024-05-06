package com.example.jobms.jobpart.dto;

import com.example.jobms.jobpart.external.model.Company;
import com.example.jobms.jobpart.model.Job;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class JobWithCompanyDto {
    private Job job;
    private Company company;
}
