package com.base64.gamesback.appointment.repository;

import com.base64.gamesback.appointment.dto.AppointmentDataResponse;
import com.base64.gamesback.appointment.dto.AppointmentDataResponseDoctor;
import com.base64.gamesback.appointment.dto.AppointmentDataResponsePatient;
import com.base64.gamesback.appointment.entity.Appointment;
import com.base64.gamesback.auth.user.entity.Doctor;
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

    @Query(value = "SELECT DISTINCT a.appointment_id as appointmentId, a.appointment_status as appointmentStatus, a.appointment_observation as appointmentObservation, " +
            " d.user_id as doctorId, d.name as doctorName, d.last_name as doctorLastName, d.address as doctorAddress, d.phone as doctorPhone, d.email as doctorEmail, d.image as doctorImage, d.latitude, d.longitude, " +
            " p.user_id as personId, p.name as personName, p.last_name as personLastName, p.document as personDocument, p.phone as personPhone, p.address as personAddress, p.email as personEmail, " +
            " s.name as specialityName, " +
            " (select ds.start_date from main.doctor_schedule ds inner join main.doctor d on ds.doctor_id = d.user_id where ds.doctor_schedule_id = (select a.doctor_schedule_id from main.appointment a2 where a2.appointment_id = a.appointment_id)) as appointmentStart, " +
            " (select ds.end_date from main.doctor_schedule ds inner join main.doctor d on ds.doctor_id = d.user_id where ds.doctor_schedule_id = (select a.doctor_schedule_id from main.appointment a2 where a2.appointment_id = a.appointment_id)) as appointmentEnd " +
            " FROM main.appointment a inner join main.doctor d on a.doctor_id = d.user_id inner join main.speciality s on a.speciality_id = s.speciality_id inner join main.doctor_schedule ds on ds.doctor_id = d.user_id inner join main.person p on a.person_id = p.user_id WHERE a.appointment_id = :appointmentId ", nativeQuery = true)
    AppointmentDataResponse getAppointmentsByAppointmentId(@Param("appointmentId") UUID appointmentId);

    @Query(value = "SELECT DISTINCT a.appointment_id as appointmentId, a.appointment_status as appointmentStatus, a.appointment_observation as appointmentObservation, " +
            " d.user_id as doctorId, d.name as doctorName, d.last_name as doctorLastName, d.address as doctorAddress, d.phone as doctorPhone, d.email as doctorEmail, d.image as doctorImage, d.latitude, d.longitude, " +
            " s.name as specialityName, " +
            " (select ds.start_date from main.doctor_schedule ds inner join main.doctor d on ds.doctor_id = d.user_id where ds.doctor_schedule_id = (select a.doctor_schedule_id from main.appointment a2 where a2.appointment_id = a.appointment_id)) as appointmentStart, " +
            " (select ds.end_date from main.doctor_schedule ds inner join main.doctor d on ds.doctor_id = d.user_id where ds.doctor_schedule_id = (select a.doctor_schedule_id from main.appointment a2 where a2.appointment_id = a.appointment_id)) as appointmentEnd " +
            " FROM main.appointment a inner join main.doctor d on a.doctor_id = d.user_id inner join main.speciality s on a.speciality_id = s.speciality_id inner join main.doctor_schedule ds on ds.doctor_id = d.user_id inner join main.person p on a.person_id = p.user_id where p.user_id = :personId ", nativeQuery = true)
    List<AppointmentDataResponsePatient> getAppointmentsByPersonId(@Param("personId") UUID personId);

    @Query(value = "select DISTINCT a.appointment_id as appointmentId, a.appointment_status as appointmentStatus, a.appointment_observation as appointmentObservation, " +
            " p.user_id as personId, p.name as personName, p.last_name as personLastName, p.document as personDocument, p.phone as personPhone, p.address as personAddress, p.email as personEmail, " +
            " s.name as specialityName, " +
            " (select ds.start_date from main.doctor_schedule ds inner join main.doctor d on ds.doctor_id = d.user_id where ds.doctor_schedule_id = (select a.doctor_schedule_id from main.appointment a2 where a2.appointment_id = a.appointment_id)) as appointmentStart, " +
            " (select ds.end_date from main.doctor_schedule ds inner join main.doctor d on ds.doctor_id = d.user_id where ds.doctor_schedule_id = (select a.doctor_schedule_id from main.appointment a2 where a2.appointment_id = a.appointment_id)) as appointmentEnd " +
            " from main.appointment a inner join main.doctor d on a.doctor_id = d.user_id inner join main.speciality s on s.speciality_id = a.speciality_id inner join main.person p on a.person_id = p.user_id where d.user_id = :doctorId ", nativeQuery = true)
    List<AppointmentDataResponseDoctor> getAppointmentsByDoctorId(@Param("doctorId") UUID doctorId);

    Boolean existsByPersonAndDoctorAndSpecialityAndAppointmentStatusIgnoreCase(Person person, Doctor doctor, Speciality speciality, String appointmentStatus);
}