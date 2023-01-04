package com.example.eterationsimplebankingcase.repository;

import com.example.eterationsimplebankingcase.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    public Account findAccountByOwnerAndActiveTrue(String ownerName);

    public Account findAccountByAccountNumber(String accountNumber);
    public Account findAccountByAccountNumberAndActiveTrue(String accountNumber);
}
