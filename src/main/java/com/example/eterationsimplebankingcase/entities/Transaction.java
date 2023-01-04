package com.example.eterationsimplebankingcase.entities;

import com.example.eterationsimplebankingcase.enums.TransactionType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.ZonedDateTime;
@EntityListeners({AuditingEntityListener.class})
@NoArgsConstructor
@Data
@Entity
public class Transaction extends BaseEntity {

    @CreatedDate
    private ZonedDateTime date;
    private Double amount;
    private TransactionType transactionType;

    private String approvalCode;

    public Transaction (Double amount){
        this.amount = amount;
        this.date = ZonedDateTime.now();
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "date=" + date +
                ", amount=" + amount +
                '}';
    }
}
