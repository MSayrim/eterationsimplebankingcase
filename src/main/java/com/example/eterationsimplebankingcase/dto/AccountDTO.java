package com.example.eterationsimplebankingcase.dto;

import com.example.eterationsimplebankingcase.entities.Transaction;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AccountDTO extends BaseEntityDTO implements Serializable {
    private String owner;
    private String accountNumber;
    private double balance;
    private List<Transaction> transactions;
}
