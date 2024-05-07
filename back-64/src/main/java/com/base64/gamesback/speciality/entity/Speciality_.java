package com.base64.gamesback.speciality.entity;

import com.base64.gamesback.auth.user.entity.Doctor;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

import javax.annotation.processing.Generated;
import java.util.UUID;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Speciality.class)
public abstract class Speciality_ {

        public static volatile SingularAttribute<Speciality, UUID> specialityId;
        public static volatile SingularAttribute<Speciality, String> name;
        public static volatile ListAttribute<Speciality, Doctor> doctor;

}
