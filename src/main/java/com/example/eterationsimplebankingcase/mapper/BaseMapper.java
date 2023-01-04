package com.example.eterationsimplebankingcase.mapper;

import com.example.eterationsimplebankingcase.dto.BaseEntityDTO;
import com.example.eterationsimplebankingcase.entities.BaseEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Named;

import java.util.List;

public interface BaseMapper<E extends BaseEntity, D extends BaseEntityDTO> {

    @Named("toEntity")
    E toEntity(D dto);

    @Named("toDTO")
    D toDTO(E entity);

    @IterableMapping(qualifiedByName = "toEntity")
    List<E> toEntityList(List<D> dtoList);

    @IterableMapping(qualifiedByName = "toDTO")
    List<D> toDTOList(List<E> entityList);

}
