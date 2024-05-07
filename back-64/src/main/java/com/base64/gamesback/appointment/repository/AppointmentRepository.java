package com.base64.gamesback.appointment.repository;

import com.base64.gamesback.appointment.entity.Appointment;
import com.base64.gamesback.auth.user.dto.projection.userPersonData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

        List<Appointment> getAppointmentsByPersonPersonId(UUID uuid);


        @Query(value = "SELECT appointment_id, doctor_id, address, date, speciality, \"time\"\n" +
                "\tFROM main.appointment as u WHERE u.person_id = :personId", nativeQuery = true)
        userPersonData getAppointmentsByUserId(@Param("personId") UUID userId);
}
