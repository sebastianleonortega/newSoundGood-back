package com.base64.gamesback.appointment.service.impl;

import com.base64.gamesback.appointment.dto.*;
import com.base64.gamesback.appointment.entity.Appointment;
import com.base64.gamesback.appointment.repository.AppointmentRepository;
import com.base64.gamesback.appointment.service.AppointmentService;
import com.base64.gamesback.auth.user.dto.DoctorScheduleUpdateRequest;
import com.base64.gamesback.auth.user.entity.Doctor;
import com.base64.gamesback.auth.user.entity.DoctorSchedule;
import com.base64.gamesback.auth.user.entity.Person;
import com.base64.gamesback.auth.user.service.DoctorScheduleService;
import com.base64.gamesback.auth.user.service.DoctorService;
import com.base64.gamesback.auth.user.service.PersonService;
import com.base64.gamesback.common.exception_handler.ResourceNotFoundException;
import com.base64.gamesback.speciality.entity.Speciality;
import com.base64.gamesback.speciality.service.SpecialityService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    private final DoctorService doctorService;

    private final PersonService personService;

    private final SpecialityService specialityService;

    private final DoctorScheduleService doctorScheduleService;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, DoctorService doctorService, PersonService personService, SpecialityService specialityService, DoctorScheduleService doctorScheduleService) {
        this.appointmentRepository = appointmentRepository;
        this.doctorService = doctorService;
        this.personService = personService;
        this.specialityService = specialityService;
        this.doctorScheduleService = doctorScheduleService;
    }

    @Override
    public void registerAppointment(AppointmentRequest request) {
        Doctor doctor = doctorService.getDoctorById(request.getDoctorId());
        Person person = personService.getPersonByID(request.getPersonId());
        Speciality speciality = specialityService.getSpecialityById(request.getSpeciality());
        DoctorSchedule doctorSchedule = doctorScheduleService.getDoctorScheduleById(request.getDoctorScheduleId());

        if(Boolean.TRUE.equals(appointmentRepository.existsByPersonAndDoctorAndSpecialityAndAppointmentStatusIgnoreCase(person, doctor, speciality, "CONFIRMADA"))){
            throw new IllegalArgumentException("Ya existe una cita para ti, con el mismo doctor, especialidad");
        }

        Appointment appointment = Appointment.create(
                person,
                doctor,
                speciality,
                doctorSchedule
        );
        doctorScheduleService.updateDoctorSchedule(new DoctorScheduleUpdateRequest(doctorSchedule.getDoctorScheduleId(), false));
        appointmentRepository.save(appointment);
    }

    @Override
    public void updateAppointment(AppointmentUpdateRequest appointmentUpdateRequest) {
        Appointment appointment = this.getAppointmentById(appointmentUpdateRequest.getAppointmentId());
        appointment.updateStatusAndObservation(appointmentUpdateRequest.getAppointmentStatus(), appointmentUpdateRequest.getAppointmentObservation());
        if(appointmentUpdateRequest.getAppointmentStatus().equalsIgnoreCase("CANCELADA") && !appointment.getDoctorSchedule().getEndDate().isBefore(LocalDateTime.now())){
            doctorScheduleService.updateDoctorSchedule(new DoctorScheduleUpdateRequest(appointment.getDoctorSchedule().getDoctorScheduleId(), true));
        }
        appointmentRepository.save(appointment);
    }

    @Transactional
    @Override
    public void deleteAppointment(UUID appointmentId) {
        Appointment appointment = this.getAppointmentById(appointmentId);
        if(appointment.getDoctorSchedule().getEndDate().isBefore(LocalDateTime.now())){
            doctorScheduleService.updateDoctorSchedule(new DoctorScheduleUpdateRequest(appointment.getDoctorSchedule().getDoctorScheduleId(), true));
        }
        appointmentRepository.delete(appointment);
    }

    @Override
    public List<AppointmentDataResponsePatient> getAppointmentByPersonId(UUID personId) {
        return appointmentRepository.getAppointmentsByPersonId(personId);
    }

    @Override
    public List<AppointmentDataResponseDoctor> getAppointmentByDoctorId(UUID doctorId) {
        return appointmentRepository.getAppointmentsByDoctorId(doctorId);
    }

    @Override
    public AppointmentDataResponse getAppointmentByAppointmentId(UUID appointmentId) {
        return appointmentRepository.getAppointmentsByAppointmentId(appointmentId);
    }

    @Override
    public Appointment getAppointmentById(UUID appointmentId) {
        return appointmentRepository.findById(appointmentId).orElseThrow(() -> new ResourceNotFoundException("No existe la cita buscada"));
    }

}
