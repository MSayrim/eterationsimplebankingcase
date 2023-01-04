package com.example.eterationsimplebankingcase.service;

import com.example.eterationsimplebankingcase.mapper.BaseMapper;
import com.example.eterationsimplebankingcase.mapper.TransactionMapper;
import com.example.eterationsimplebankingcase.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;


    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

}
