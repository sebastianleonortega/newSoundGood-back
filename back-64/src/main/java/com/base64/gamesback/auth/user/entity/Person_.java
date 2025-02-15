package com.base64.gamesback.auth.user.entity;

import com.base64.gamesback.documentType.entity.DocumentType;
import com.base64.gamesback.genderType.entity.GenderType;
import com.base64.gamesback.hearing_loss.entity.HearingLoss;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

import javax.annotation.processing.Generated;
import java.time.LocalDateTime;
import java.util.UUID;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Person_.class)
public abstract class Person_ {
    public static volatile SingularAttribute<Person, UUID> personId;
    public static volatile SingularAttribute<Person, User> user;
    public static volatile SingularAttribute<Person, String> personName;
    public static volatile SingularAttribute<Person, String> personLastName;
    public static volatile SingularAttribute<Person, DocumentType> documentType;
    public static volatile SingularAttribute<Person, GenderType> genderType;
    public static volatile SingularAttribute<Person, String> personDocument;
    public static volatile SingularAttribute<Person, String> personAddress;
    public static volatile SingularAttribute<Person, String> personPhone;
    public static volatile SingularAttribute<Person, String> personEmail;
    public static volatile SingularAttribute<Person, String> previousTreatments;
    public static volatile SetAttribute<Person, HearingLoss> hearingLosses;
    public static volatile SingularAttribute<Person, LocalDateTime> createdAt;
    public static volatile SingularAttribute<Person, LocalDateTime> updateAt;
}
