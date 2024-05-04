package com.base64.gamesback.genderType.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

import javax.annotation.processing.Generated;
import java.util.UUID;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(GenderType.class)
public abstract class GenderType_ {

    public static volatile SingularAttribute<GenderType, UUID> genderId;
    public static volatile SingularAttribute<GenderType, String> name;
    public static volatile SingularAttribute<GenderType, String> code;

}
