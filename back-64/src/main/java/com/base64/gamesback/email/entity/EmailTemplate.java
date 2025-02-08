package com.base64.gamesback.email.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@Entity
@Table(name = "email_template", schema = "main")
public class EmailTemplate {

    @Id
    @Column(name = "name", unique = true, length = 100)
    private String name;

    @Column(name = "body", columnDefinition = "text")
    private String body;

    public void replaceBody(String body){
        this.body = body;
    }
}
