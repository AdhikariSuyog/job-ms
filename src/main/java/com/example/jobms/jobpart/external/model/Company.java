package com.example.jobms.jobpart.external.model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Company {
    private Long id;
    private String name;
    private String description;

    public static boolean isEmpty(Company company) {
        return (company.getName() == null || company.getName().isEmpty())
                && (company.getDescription() == null || company.getDescription().isEmpty());
    }
}
