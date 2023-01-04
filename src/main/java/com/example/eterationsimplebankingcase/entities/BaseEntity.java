package com.example.eterationsimplebankingcase.entities;

import com.example.eterationsimplebankingcase.util.IDListener;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.ZonedDateTime;

@EntityListeners({AuditingEntityListener.class, IDListener.class})
@MappedSuperclass
@Data
@EqualsAndHashCode(of = "id")
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 2198096043152933245L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private ZonedDateTime creationDate;

    @LastModifiedDate
    @Column(nullable = false)
    private ZonedDateTime modificationDate;

    private Boolean active = true;
    private String uuid;


}