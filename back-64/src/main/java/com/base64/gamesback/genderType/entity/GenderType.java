package com.base64.gamesback.genderType.entity;

import com.base64.gamesback.common.util.AuditEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Entity
@Table(name = "gender_type", schema = "main")
@EntityListeners(EntityListeners.class)
@NoArgsConstructor
public class GenderType extends AuditEntity {

    @Id
    @GeneratedValue
    @Column(name = "gender_type_id", unique = true)
    private UUID genderId;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "code", unique = true)
    private String code;

}
