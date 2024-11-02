package com.project.management.employee.mapper;

import com.project.management.employee.dto.EmployeeDto;
import com.project.management.employee.entity.Employee;

import java.util.Optional;

public class EmployeeMapper {


    public static EmployeeDto mapToEmployeeDto(Employee employee){
        return EmployeeDto.builder().id(employee.getId()).emailId(employee.getEmailId()).lastName(employee.getLastName()).firstName(employee.getFirstName()).build();
    }

    public static Employee mapToEmployee(EmployeeDto employeeDto){
        return Employee.builder().id(employeeDto.getId()).emailId(employeeDto.getEmailId()).lastName(employeeDto.getLastName()).firstName(employeeDto.getFirstName()).build();
    }
}
