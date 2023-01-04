package com.example.eterationsimplebankingcase.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class TransactionDTO extends BaseEntityDTO implements Serializable {
    private Double amount;
    private String companyName;
    private String billCode;
}
