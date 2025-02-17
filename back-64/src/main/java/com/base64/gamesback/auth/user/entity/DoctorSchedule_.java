package com.base64.gamesback.auth.user.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

import javax.annotation.processing.Generated;
import java.time.LocalDateTime;
import java.util.UUID;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DoctorSchedule.class)
public abstract class DoctorSchedule_ {

    public static volatile SingularAttribute<DoctorSchedule, UUID> doctorScheduleId;
    public static volatile SingularAttribute<DoctorSchedule, Doctor> doctor;
    public static volatile SingularAttribute<DoctorSchedule, LocalDateTime> startDate;
    public static volatile SingularAttribute<DoctorSchedule, LocalDateTime> endDate;
    public static volatile SingularAttribute<DoctorSchedule, Boolean> available;
}
