package com.example.eterationsimplebankingcase.controller;

import com.example.eterationsimplebankingcase.dto.AccountDTO;
import com.example.eterationsimplebankingcase.dto.ResponseDTO;
import com.example.eterationsimplebankingcase.dto.TransactionDTO;
import com.example.eterationsimplebankingcase.exception.AccountNotFoundException;
import com.example.eterationsimplebankingcase.exception.InsufficientBalanceException;
import com.example.eterationsimplebankingcase.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;
    @PostMapping("/create")
    public AccountDTO createOrUpdateAccount(@RequestBody AccountDTO accountDTO){

        return accountService.create(accountDTO);
    }
    @PostMapping("/credit/{accountNumber}")
    public ResponseDTO credit(@RequestBody TransactionDTO transactionDTO, @PathVariable String accountNumber) throws AccountNotFoundException {
        return accountService.deposit(accountNumber,transactionDTO);
    }
    @PostMapping("/debit/{accountNumber}")
    public ResponseDTO debit(@RequestBody TransactionDTO transactionDTO,@PathVariable String accountNumber) throws InsufficientBalanceException, AccountNotFoundException {
        return accountService.withdrawal(accountNumber,transactionDTO);
    }
    @PostMapping("/payment/{accountNumber}")
    public List<ResponseDTO> payment(@RequestBody TransactionDTO transactionDTO, @PathVariable String accountNumber) throws InsufficientBalanceException, AccountNotFoundException {
        return accountService.payment(accountNumber,transactionDTO);
    }
    @GetMapping("/accountDetail/{accountNumber}")
    public AccountDTO getAccountDetail(@PathVariable String accountNumber) throws AccountNotFoundException {
        return accountService.accountDetail(accountNumber);
    }
}
