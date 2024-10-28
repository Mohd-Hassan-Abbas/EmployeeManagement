package com.project.management.employee.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String emailId;
}
