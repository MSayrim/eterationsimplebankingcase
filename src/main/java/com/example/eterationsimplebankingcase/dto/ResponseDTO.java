package com.example.eterationsimplebankingcase.dto;

import com.example.eterationsimplebankingcase.enums.Status;
import lombok.Data;

@Data
public class ResponseDTO {
    private String approvelCode;
    private Status status;
}
