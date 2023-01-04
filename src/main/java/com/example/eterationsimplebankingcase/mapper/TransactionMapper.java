package com.example.eterationsimplebankingcase.mapper;

import com.example.eterationsimplebankingcase.dto.AccountDTO;
import com.example.eterationsimplebankingcase.dto.TransactionDTO;
import com.example.eterationsimplebankingcase.entities.Transaction;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper extends BaseMapper<Transaction, TransactionDTO>{
    @Named("toEntity")
    @Mapping(target = "creationDate",ignore=true)
    Transaction toEntity(TransactionDTO dto);

    @Named("toDTO")
    TransactionDTO toDTO(Transaction entity);

    @IterableMapping(qualifiedByName = "toEntity")
    List<Transaction> toEntityList(List<TransactionDTO> dtoList);

    @IterableMapping(qualifiedByName = "toDTO")
    List<TransactionDTO> toDTOList(List<Transaction> entityList);
}
