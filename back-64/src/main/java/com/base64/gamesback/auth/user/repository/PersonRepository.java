package com.base64.gamesback.auth.user.repository;

import com.base64.gamesback.auth.user.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {

    Boolean existsPersonByPersonEmail(String personEmail);

    Boolean existsPersonByPersonDocument(String personDocument);
}
