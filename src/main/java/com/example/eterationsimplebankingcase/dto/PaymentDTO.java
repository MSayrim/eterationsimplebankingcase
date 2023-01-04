package com.example.eterationsimplebankingcase.dto;

import lombok.Data;

import java.util.List;

@Data
public class PaymentDTO {
    private String companyName;
    private String billCode;
    private List<ResponseDTO> responseDTOList;
}
