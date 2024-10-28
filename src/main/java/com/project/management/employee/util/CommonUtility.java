package com.project.management.employee.util;

import com.project.management.employee.dto.DataResponse;

public class CommonUtility {

    public static<T> DataResponse<T> getDataResponse(Integer responseCode, String message, T data){
        DataResponse<T> dataResponse = new DataResponse<>();
        dataResponse.setData(data);
        dataResponse.setResponseCode(responseCode);
        dataResponse.setMessage(message);

        return dataResponse;
    }
}
