package com.base64.gamesback.medicines.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "medicine", schema = "main")
@Getter
@NoArgsConstructor
public class Medicine {

    @Id
    @GeneratedValue
    @Column(name = "medicine_id")
    private UUID medicineId;

    @Column(name = "name")
    private String name;

    public Medicine(String name) {
        this.name = name;
    }

    public void updateMedicine(String name) {
        this.name = name;
    }

    public static Medicine create(String name) {
        return new Medicine(
                name
        );
    }

}
