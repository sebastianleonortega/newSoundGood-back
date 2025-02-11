package com.base64.gamesback.auth.user.entity;

import com.base64.gamesback.documentType.entity.DocumentType;
import com.base64.gamesback.genderType.entity.GenderType;
import com.base64.gamesback.speciality.entity.Speciality;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

import javax.annotation.processing.Generated;
import java.util.UUID;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Doctor.class)
public abstract class Doctor_ {

        public static volatile SingularAttribute<Doctor, String> lastName;
        public static volatile SingularAttribute<Doctor, String> image;
        public static volatile SetAttribute<Doctor, Speciality> speciality;
        public static volatile SingularAttribute<Doctor, String> name;
        public static volatile SingularAttribute<Doctor, String> description;
        public static volatile SingularAttribute<Doctor, UUID> doctorId;
        public static volatile SingularAttribute<Doctor, User> user;
        public static volatile SingularAttribute<Doctor, String> address;
        public static volatile SingularAttribute<Doctor, String> phone;
        public static volatile SingularAttribute<Doctor, String> document;
        public static volatile SingularAttribute<Doctor, String> email;
        public static volatile SingularAttribute<Doctor, String> latitude;
        public static volatile SingularAttribute<Doctor, String> longitude;
        public static volatile SingularAttribute<Doctor, DocumentType> documentType;
        public static volatile SingularAttribute<Doctor, GenderType> genderType;

}
