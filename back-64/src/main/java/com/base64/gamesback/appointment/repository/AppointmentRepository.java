package com.base64.gamesback.appointment.repository;

import com.base64.gamesback.appointment.dto.AppointmentDataResponse;
import com.base64.gamesback.appointment.entity.Appointment;
import com.base64.gamesback.auth.user.dto.projection.userPersonData;
import com.base64.gamesback.auth.user.entity.Doctor;
import com.base64.gamesback.auth.user.entity.Doctor_;
import com.base64.gamesback.auth.user.entity.Person;
import com.base64.gamesback.speciality.entity.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

        List<Appointment> getAppointmentsByPersonPersonId(UUID uuid);

        @Query(value = "SELECT a.appointment_id as appointmentId, d.user_id as doctorId, s.speciality_id as specialityId, s.name as specialityName, d.name as doctorName, d.last_name as doctorLastName, d.phone as doctorPhone, d.image as doctorImage, " +
                "p.name as personName, p.last_name as personLastName, p.phone as personPhone, a.address as appointmentAddress, a.date as appointmentDate,  a.time as appointmentTime " +
                "FROM main.appointment a inner join main.doctor d on a.doctor_id = d.user_id inner join main.speciality s on a.speciality_id = s.speciality_id " +
                "inner join main.person p on a.person_id = p.user_id WHERE a.appointment_id = :appointmentId", nativeQuery = true)
        AppointmentDataResponse getAppointmentsByAppointmentId(@Param("appointmentId") UUID appointmentId);

        @Query(value = "SELECT a.appointment_id as appointmentId, d.user_id as doctorId, s.speciality_id as specialityId, s.name as specialityName, d.name as doctorName, d.last_name as doctorLastName, d.latitude as doctorLatitude, d.longitude as doctorLongitude , d.phone as doctorPhone, d.image as doctorImage, " +
                "p.name as personName, p.last_name as personLastName, p.phone as personPhone, a.address as appointmentAddress, a.date as appointmentDate,  a.time as appointmentTime " +
                "FROM main.appointment a inner join main.doctor d on a.doctor_id = d.user_id inner join main.speciality s on a.speciality_id = s.speciality_id " +
                "inner join main.person p on a.person_id = p.user_id WHERE p.user_id = :personId", nativeQuery = true)
        List<AppointmentDataResponse> getAppointmentsByPersonId(@Param("personId") UUID personId);

        @Query(value = "SELECT a.appointment_id as appointmentId, d.user_id as doctorId, s.speciality_id as specialityId, s.name as specialityName, d.name as doctorName, d.last_name as doctorLastName, d.phone as doctorPhone, d.image as doctorImage, " +
                "p.name as personName, p.last_name as personLastName, p.phone as personPhone, a.address as appointmentAddress, a.date as appointmentDate,  a.time as appointmentTime " +
                "FROM main.appointment a inner join main.doctor d on a.doctor_id = d.user_id inner join main.speciality s on a.speciality_id = s.speciality_id " +
                "inner join main.person p on a.person_id = p.user_id WHERE d.user_id = :doctorId", nativeQuery = true)
        List<AppointmentDataResponse> getAppointmentsByDoctorId(@Param("doctorId") UUID doctorId);

        Boolean existsByPersonAndDoctorAndSpecialityAndDateAndTimeAndAddress(Person person, Doctor doctor, Speciality speciality, String date, String time, String address);
}
