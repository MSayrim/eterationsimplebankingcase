package com.example.eterationsimplebankingcase.entities;

import com.example.eterationsimplebankingcase.enums.TransactionType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@Data
public class BillPaymentTransaction extends Transaction {
    private Long ownerID;
    private Long recieverID;
    private String transactionInfo;
    public BillPaymentTransaction(Long companyID,String billCode,Long ownerID,Double amount) {
        this.setTransactionType(TransactionType.DepositTransaction);
        this.recieverID=companyID;
        this.ownerID=ownerID;
        this.transactionInfo = "Paid invoice number :" + billCode;
        setApprovalCode(UUID.randomUUID().toString());
        this.setAmount(amount);
    }
}
