package com.base64.gamesback.auth.user.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

import javax.annotation.processing.Generated;
import java.time.LocalDateTime;
import java.util.UUID;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

        public static volatile SingularAttribute<User, UUID> userId;
        public static volatile SingularAttribute<User, Person> person;
        public static volatile SingularAttribute<User, String> userName;
        public static volatile SingularAttribute<User, String> password;
        public static volatile SingularAttribute<User, String> status;
        public static volatile SingularAttribute<User, Integer> loginAttempts;
        public static volatile SingularAttribute<User, Integer> loginAttemptsMfa;
        public static volatile SingularAttribute<User, Boolean> administrator;
        public static volatile SingularAttribute<User, String> profileImage;
        public static volatile SingularAttribute<User, Doctor> doctor;
        public static volatile SingularAttribute<User, Boolean> havePasswordByAdmin;
        public static volatile SingularAttribute<User, String> codeVerification;
        public static volatile SingularAttribute<User, LocalDateTime> createCodeVerification;
        public static volatile SingularAttribute<User, Integer> quantityResentEmail;
        public static volatile SingularAttribute<User, LocalDateTime> resetPasswordUpdateAt;
        public static volatile SingularAttribute<User, LocalDateTime> createdAt;
        public static volatile SingularAttribute<User, LocalDateTime> updateAt;
        public static volatile SingularAttribute<User, LocalDateTime> lastLogin;
}
