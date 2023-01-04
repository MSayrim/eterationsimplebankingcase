package com.example.eterationsimplebankingcase.util;

import com.example.eterationsimplebankingcase.entities.BaseEntity;
import jakarta.persistence.PrePersist;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class IDListener {

    @PrePersist
    public void createUUID(BaseEntity baseEntity) {
        baseEntity.setUuid(UUID.randomUUID().toString());
    }

}
