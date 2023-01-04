package com.example.eterationsimplebankingcase.dto;

import lombok.Data;

@Data
public class AccountRequestDTO {
    private String owner;
    private String accountNumber;
}
