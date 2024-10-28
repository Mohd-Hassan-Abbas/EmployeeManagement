package com.project.management.employee.controller;

import com.project.management.employee.dto.DataResponse;
import com.project.management.employee.dto.EmployeeDto;
import com.project.management.employee.service.EmployeeService;
import com.project.management.employee.util.CommonUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    public ResponseEntity<DataResponse<EmployeeDto>> createAccount(@RequestBody EmployeeDto employeeDto){

        EmployeeDto savedEmployeeDto = employeeService.createEmployee(employeeDto);

        DataResponse<EmployeeDto> dataResponse = CommonUtility.getDataResponse(200,"CREATED", savedEmployeeDto);

        return ResponseEntity.ok(dataResponse);

    }
}
