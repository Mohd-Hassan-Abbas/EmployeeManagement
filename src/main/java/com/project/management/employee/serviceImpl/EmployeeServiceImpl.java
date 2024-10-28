package com.project.management.employee.serviceImpl;

import com.project.management.employee.dto.EmployeeDto;
import com.project.management.employee.entity.Employee;
import com.project.management.employee.mapper.EmployeeMapper;
import com.project.management.employee.repository.EmployeeRepository;
import com.project.management.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }
}
