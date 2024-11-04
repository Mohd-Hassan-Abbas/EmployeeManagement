package com.project.management.employee.service;

import com.project.management.employee.dto.DataResponse;
import com.project.management.employee.dto.EmployeeDto;
import com.project.management.employee.entity.Employee;

import java.util.List;

public interface EmployeeService {

    DataResponse<EmployeeDto> createEmployee(EmployeeDto employeeDto);

    List<Employee> getAllEmployee();

    DataResponse<EmployeeDto> getById(Long id);

    boolean deleteById(Long id);
}
