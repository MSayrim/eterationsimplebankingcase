package com.example.eterationsimplebankingcase.mapper;

import com.example.eterationsimplebankingcase.dto.AccountDTO;
import com.example.eterationsimplebankingcase.entities.Account;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper extends BaseMapper<Account, AccountDTO>{
    @Named("toEntity")
    @Mapping(target = "creationDate",ignore=true)
    Account toEntity(AccountDTO dto);

    @Named("toDTO")
    AccountDTO toDTO(Account entity);

    @IterableMapping(qualifiedByName = "toEntity")
    List<Account> toEntityList(List<AccountDTO> dtoList);

    @IterableMapping(qualifiedByName = "toDTO")
    List<AccountDTO> toDTOList(List<Account> entityList);
}
