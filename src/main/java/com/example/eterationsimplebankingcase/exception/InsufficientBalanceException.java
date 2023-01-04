package com.example.eterationsimplebankingcase.exception;

import com.example.eterationsimplebankingcase.enums.Status;
import lombok.NoArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE, reason = "Not enough balance")
public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message){
        super(message);
    }
}
