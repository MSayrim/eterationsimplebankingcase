package com.example.eterationsimplebankingcase.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Entity
@Data
public class Account extends BaseEntity implements Serializable {

    private String owner;
    private String accountNumber;
    public Account(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
    }

    private double balance;

    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.REMOVE,CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "owner_id")
    private List<Transaction> transactions;


}
