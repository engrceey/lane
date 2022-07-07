package com.zurum.lanefinance.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zurum.lanefinance.constants.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ApiResponse<T> {

    private String statusMessage;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String statusCode;
    private Boolean isSuccessful;
    private final LocalDateTime time = LocalDateTime.now();
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public static  <T>ApiResponse<T>  buildSuccessTxn(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setIsSuccessful(true);
        response.setStatusCode(ResponseStatus.SUCCESSFUL.getCode());
        response.setStatusMessage(ResponseStatus.SUCCESSFUL.getMessage());
        response.setData(data);
        return response;
    }

    public static  <T>ApiResponse<T>  buildSuccess(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setIsSuccessful(true);
        response.setStatusMessage(ResponseStatus.SUCCESSFUL.getMessage());
        response.setData(data);
        return response;
    }

    public static ApiResponse<Void> build() {
        ApiResponse<Void> response = new ApiResponse<>();
        response.setIsSuccessful(true);
        response.setStatusMessage(ResponseStatus.SUCCESSFUL.getMessage());
        return response;
    }

}