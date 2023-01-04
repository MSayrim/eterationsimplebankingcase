package com.example.eterationsimplebankingcase.entities;

import com.example.eterationsimplebankingcase.enums.TransactionType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@Data
public class DepositTransaction extends Transaction {
    private Long ownerID;
    public DepositTransaction(Long ownerID,Double amount) {
        this.setTransactionType(TransactionType.DepositTransaction);
        this.ownerID=ownerID;
        setApprovalCode(UUID.randomUUID().toString());
        this.setAmount(amount);
    }
}
