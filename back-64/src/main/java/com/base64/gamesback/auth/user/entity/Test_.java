package com.base64.gamesback.auth.user.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

import javax.annotation.processing.Generated;
import java.time.LocalDateTime;
import java.util.UUID;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Test.class)
public abstract class Test_ {
    public static volatile SingularAttribute<Test, UUID> testId;
    public static volatile SingularAttribute<Test, Person> person;
    public static volatile SingularAttribute<Test, String> testType;
    public static volatile SingularAttribute<Test, LocalDateTime> testDate;
    public static volatile SingularAttribute<Test, String> result;

}
