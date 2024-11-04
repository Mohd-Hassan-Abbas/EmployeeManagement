package com.project.management.employee.controller;

import com.project.management.employee.dto.DataResponse;
import com.project.management.employee.dto.EmployeeDto;
import com.project.management.employee.entity.Employee;
import com.project.management.employee.service.EmployeeService;
import com.project.management.employee.util.CommonUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.naming.ldap.PagedResultsControl;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    private final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @PostMapping("/create")
    public ResponseEntity<DataResponse<EmployeeDto>> createAccount(@RequestBody EmployeeDto employeeDto){


        if(ObjectUtils.isEmpty(employeeDto)){
            DataResponse<EmployeeDto> dataResponse = CommonUtility.getDataResponse(400,"BAD_REQUEST", null);

            return ResponseEntity.ok(dataResponse);

        }

        LOGGER.info("Data Received: {}",employeeDto.toString());

        DataResponse<EmployeeDto> savedEmployeeDtoDR = employeeService.createEmployee(employeeDto);

        if(ObjectUtils.isEmpty(savedEmployeeDtoDR.getData())){
            return ResponseEntity.ok(savedEmployeeDtoDR);

        }

        DataResponse<EmployeeDto> dataResponse = CommonUtility.getDataResponse(200,"CREATED", savedEmployeeDtoDR.getData());

        return ResponseEntity.ok(dataResponse);

    }

    @GetMapping("/get/all")
    public ResponseEntity<DataResponse<List<Employee>>> getAll(){

        List<Employee> allEmployee = employeeService.getAllEmployee();

        DataResponse<List<Employee>> dataResponse = CommonUtility.getDataResponse(allEmployee.isEmpty()?404:200, allEmployee.isEmpty()?"NOT_FOUND":"CREATED",allEmployee.isEmpty()?null:allEmployee);

        return ResponseEntity.ok(dataResponse);

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<DataResponse<EmployeeDto>> getById(@PathVariable("id") String Id){

        DataResponse<EmployeeDto> employee = employeeService.getById(Long.parseLong(Id));

        return ResponseEntity.ok(employee);

    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteById(@PathVariable("id") String Id){

        return employeeService.deleteById(Long.parseLong(Id));
    }
}
