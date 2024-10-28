package com.project.management.employee.dto;

import lombok.Data;

@Data
public class DataResponse<T> {
    private Integer responseCode;
    private String message;
    private T data;
}
