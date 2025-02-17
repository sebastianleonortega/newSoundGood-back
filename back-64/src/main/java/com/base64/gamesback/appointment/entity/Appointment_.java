package com.base64.gamesback.appointment.entity;

import com.base64.gamesback.auth.user.entity.Doctor;
import com.base64.gamesback.auth.user.entity.DoctorSchedule;
import com.base64.gamesback.auth.user.entity.Person;
import com.base64.gamesback.auth.user.entity.User;
import com.base64.gamesback.documentType.entity.DocumentType;
import com.base64.gamesback.genderType.entity.GenderType;
import com.base64.gamesback.speciality.entity.Speciality;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

import javax.annotation.processing.Generated;
import java.time.LocalDateTime;
import java.util.UUID;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Appointment.class)
public abstract class Appointment_ {

    public static volatile SingularAttribute<Appointment, UUID> appointmentId;
    public static volatile SingularAttribute<Appointment, String> appointmentStatus;
    public static volatile SingularAttribute<Appointment, Person> person;
    public static volatile SingularAttribute<Appointment, Doctor> doctor;
    public static volatile SetAttribute<Appointment, Speciality> speciality;
    public static volatile SetAttribute<Appointment, DoctorSchedule> doctorSchedule;
    public static volatile SingularAttribute<Appointment, LocalDateTime> createdAt;
    public static volatile SingularAttribute<Appointment, LocalDateTime> updateAt;

}
