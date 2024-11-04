package com.project.management.employee.serviceImpl;

import com.project.management.employee.dto.DataResponse;
import com.project.management.employee.dto.EmployeeDto;
import com.project.management.employee.entity.Employee;
import com.project.management.employee.mapper.EmployeeMapper;
import com.project.management.employee.repository.EmployeeRepository;
import com.project.management.employee.service.EmployeeService;
import com.project.management.employee.util.CommonUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.ListResourceBundle;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public DataResponse<EmployeeDto> createEmployee(EmployeeDto employeeDto) {

        if(ObjectUtils.isEmpty(employeeDto.getFirstName())){
            String message = "Employee FirstName is NULL";
            LOGGER.info(message);
            return CommonUtility.getDataResponse(400,message,null);
        }

        if(ObjectUtils.isEmpty(employeeDto.getEmailId())){
             String message = "Employee EmailId is NULL";
            LOGGER.info(message);
            return CommonUtility.getDataResponse(400,message,null);

        }

        if(ObjectUtils.isEmpty(employeeDto.getId())){
            Optional<Employee> savedEmployee = employeeRepository.findByEmailId(employeeDto.getEmailId());

            if(savedEmployee.isPresent()){
                String message = "Employee with EmailId already present in DB";
                LOGGER.info(message);
                return CommonUtility.getDataResponse(400,message,null);

            }
        }



        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee newEmployee = employeeRepository.save(employee);
        EmployeeDto savedEmployeeDto =  EmployeeMapper.mapToEmployeeDto(newEmployee);
        String message = "Employee created successfully";
        LOGGER.info(message);
        return CommonUtility.getDataResponse(200,message,savedEmployeeDto);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public DataResponse<EmployeeDto> getById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            String message = "Employee with Id not present in DB";
            LOGGER.info(message);
            return CommonUtility.getDataResponse(400, message, null);

        }

        return CommonUtility.getDataResponse(200, "Found Employee ", EmployeeMapper.mapToEmployeeDto(employee.get()));

    }

    @Override
    public boolean deleteById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) {
            String message = "Data already deleted";
            LOGGER.info(message);
            return false;

        }
        employeeRepository.deleteById(id);
        return true;
    }
}
