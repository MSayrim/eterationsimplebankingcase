package com.example.eterationsimplebankingcase.service;

import com.example.eterationsimplebankingcase.dto.AccountDTO;
import com.example.eterationsimplebankingcase.dto.ResponseDTO;
import com.example.eterationsimplebankingcase.dto.TransactionDTO;
import com.example.eterationsimplebankingcase.entities.*;
import com.example.eterationsimplebankingcase.enums.Status;
import com.example.eterationsimplebankingcase.exception.AccountNotFoundException;
import com.example.eterationsimplebankingcase.exception.InsufficientBalanceException;
import com.example.eterationsimplebankingcase.mapper.AccountMapper;
import com.example.eterationsimplebankingcase.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Slf4j
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;

    @Autowired
    public AccountService(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    /**
     * Create account
     * */
    @Transactional
    public AccountDTO create(AccountDTO accountDTO) {

        Account account = accountRepository.findAccountByAccountNumber(accountDTO.getAccountNumber());

        if(Objects.nonNull(account)){
            accountDTO.setId(account.getId());
            accountDTO.setUuid(account.getUuid());
            accountDTO.setAccountNumber(account.getAccountNumber());
        }

        account = accountRepository.save(accountMapper.toEntity(accountDTO));

        return accountMapper.toDTO(account);
    }
    /**
     * Deposit
     * */
    @Transactional
    public ResponseDTO deposit(String accountNumber, TransactionDTO transactionDTO) throws AccountNotFoundException {
        log.info("Deposit progress started");
        Account account = accountRepository.findAccountByAccountNumberAndActiveTrue(accountNumber);
        if (Objects.nonNull(account)) {
            Transaction transaction = new DepositTransaction(account.getId(), transactionDTO.getAmount());
            log.info("Balance before deposit action : {}", account.getBalance());
            account.setBalance(increaseBalance(account.getBalance(), transactionDTO.getAmount()));
            log.info("Balance after deposit action : {}", account.getBalance());
            account.getTransactions().add(transaction);
            accountRepository.save(account);
            log.info("Deposit action completed approval code: {}", transaction.getApprovalCode());
            log.info("Deposit progress ended");
            return responseGenerator(transaction);
        } else {
            throw new AccountNotFoundException();
        }
    }
    /**
     * Withdrawal
     * */
    @Transactional
    public ResponseDTO withdrawal(String accountNumber, TransactionDTO transactionDTO) throws InsufficientBalanceException, AccountNotFoundException {
        log.info("Withdrawal progress started");
        Account account = accountRepository.findAccountByAccountNumberAndActiveTrue(accountNumber);
        if (Objects.nonNull(account)) {
            if (checkBalance(accountNumber, transactionDTO.getAmount())) {
                log.info("Balance before withdraw action : {}", account.getBalance());
                account.setBalance(decraseBalance(account.getBalance(), transactionDTO.getAmount()));
                log.info("Balance after withdraw action : {}", account.getBalance());
                Transaction transaction = new WithdrawalTransaction(account.getId(), transactionDTO.getAmount());
                account.getTransactions().add(transaction);
                accountRepository.save(account);
                log.info("Withdrawal action completed approval code: {}", transaction.getApprovalCode());
                log.info("Withdrawal progress ended");
                return responseGenerator(transaction);
            } else {
                throw new InsufficientBalanceException();
            }
        } else {
            throw new AccountNotFoundException();
        }

    }

    /**
     * Payment
     * */
    @Transactional
    public List<ResponseDTO> payment(String accountNumber, TransactionDTO transactionDTO) throws InsufficientBalanceException, AccountNotFoundException {
        log.info("Payment progress started");
        Account companyAccount = accountRepository.findAccountByOwnerAndActiveTrue(transactionDTO.getCompanyName());
        if (Objects.nonNull(companyAccount)) {
            List<ResponseDTO> responseDTOList = new ArrayList<>();
            log.info("Withdrawal started from payee account");
            responseDTOList.add(withdrawal(accountNumber, transactionDTO));
            log.info("Deposit started to reciever account");
            responseDTOList.add(deposit(companyAccount.getAccountNumber(), transactionDTO));
            log.info("Payment transaction started");
            Transaction bpTransaction = new BillPaymentTransaction(companyAccount.getId(), transactionDTO.getBillCode(), accountRepository.findAccountByAccountNumberAndActiveTrue(accountNumber).getId(), transactionDTO.getAmount());
            log.info("Bill payment action completed approval code: {}", bpTransaction.getApprovalCode());
            responseDTOList.add(responseGenerator(bpTransaction));
            log.info("Payment registration to company account started");
            companyAccount.getTransactions().add(bpTransaction);
            accountRepository.save(companyAccount);
            log.info("Payment registration to company account ended");
            log.info("Payment progress ended");
            return responseDTOList;
        } else {
            throw new AccountNotFoundException();
        }
    }
    /**
     * Account detail
     * */
    @Transactional
    public AccountDTO accountDetail(String accountNumber) throws AccountNotFoundException {
        log.info("Account details recieving with fallowing accountNumber:{}", accountNumber);
        Account account = accountRepository.findAccountByAccountNumberAndActiveTrue(accountNumber);
        if (Objects.nonNull(account)) {
            log.info("Account details founded");
            return accountMapper.toDTO(account);
        } else {
            throw new AccountNotFoundException();
        }
    }

    /**
     * Check ballance
     * */
    @Transactional
    public Boolean checkBalance(String accountNumber, Double amount) {
        Account account = accountRepository.findAccountByAccountNumberAndActiveTrue(accountNumber);
        if (Double.compare(account.getBalance(), amount) > 0) {
            log.info("Have enough balance");
            return true;
        } else {
            log.info("Not enough balance");
            return false;
        }
    }

    /**
     * Increase ballance
     * */
    @Transactional
    public Double increaseBalance(Double currentBalance, Double amount) {
        log.info("New balance calculated +");
        return currentBalance + amount;
    }

    /**
     * Decrease ballance
     * */
    @Transactional
    public Double decraseBalance(Double currentBalance, Double amount) {
        log.info("New balance calculated -");
        return currentBalance - amount;
    }

    /**
     * Response generator
     * */
    @Transactional
    public ResponseDTO responseGenerator(Transaction transaction) {
        log.info("Response generate progress started");
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(Status.OK);
        responseDTO.setApprovelCode(transaction.getApprovalCode());
        log.info("Response generate progress endded");
        return responseDTO;
    }

}
