package com.example.eterationsimplebankingcase.dto;

import lombok.Data;

@Data
public class ErrorResponse {
    private int status;
    private String message;
    ErrorResponse(int status,String message){
        this.status = status;
        this.message = message;
    }
}
