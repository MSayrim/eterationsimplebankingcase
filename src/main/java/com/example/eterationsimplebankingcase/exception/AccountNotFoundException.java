package com.example.eterationsimplebankingcase.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "There is no record owned that account number")
public class AccountNotFoundException extends Exception {
    public AccountNotFoundException(String message){
        super(message);
    }
}

