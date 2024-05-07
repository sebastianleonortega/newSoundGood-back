package com.base64.gamesback.reminder.entity;

import com.base64.gamesback.auth.user.entity.Person_;
import com.base64.gamesback.medicines.entity.Medicine_;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

import javax.annotation.processing.Generated;
import java.time.LocalTime;
import java.util.UUID;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Reminder.class)
public abstract class Reminder_ {
        public static volatile SingularAttribute<Reminder, UUID> reminderId;
        public static volatile SingularAttribute<Reminder, Person_> person;
        public static volatile SingularAttribute<Reminder, Medicine_> medicine;
        public static volatile SingularAttribute<Reminder, String> frequency;
        public static volatile SingularAttribute<Reminder, LocalTime> reminderTime;
}
