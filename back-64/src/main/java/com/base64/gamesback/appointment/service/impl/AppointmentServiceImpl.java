package com.base64.gamesback.appointment.service.impl;

import com.base64.gamesback.appointment.dto.AppointmentDataResponse;
import com.base64.gamesback.appointment.dto.AppointmentDto;
import com.base64.gamesback.appointment.entity.Appointment;
import com.base64.gamesback.appointment.repository.AppointmentRepository;
import com.base64.gamesback.appointment.service.AppointmentService;
import com.base64.gamesback.auth.user.entity.Doctor;
import com.base64.gamesback.auth.user.entity.Person;
import com.base64.gamesback.auth.user.repository.UserRepository;
import com.base64.gamesback.auth.user.service.DoctorService;
import com.base64.gamesback.auth.user.service.PersonService;
import com.base64.gamesback.common.exception_handler.ResourceNotFoundException;
import com.base64.gamesback.speciality.entity.Speciality;
import com.base64.gamesback.speciality.service.SpecialityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    private final DoctorService doctorService;

    private final PersonService personService;

    private final SpecialityService specialityService;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, DoctorService doctorService, PersonService personService, SpecialityService specialityService) {
        this.appointmentRepository = appointmentRepository;
        this.doctorService = doctorService;
        this.personService = personService;
        this.specialityService = specialityService;
    }

    @Override
    public void registerAppointment(AppointmentDto request) {
        Doctor doctor = doctorService.getDoctorById(request.getDoctorId());
        Person person = personService.getPersonByID(request.getPersonId());
        Speciality speciality = specialityService.getSpecialityById(request.getSpeciality());

        if(Boolean.TRUE.equals(appointmentRepository.existsByPersonAndDoctorAndSpecialityAndDateAndTimeAndAddress(person, doctor, speciality, request.getDate(), request.getTime(), request.getAddress()))){
            throw new IllegalArgumentException("Ya existe una cita para ti, con el mismo doctor, especialidad, fecha, hora y lugar");
        }

        Appointment appointment = Appointment.create(
                request.getDate(),
                request.getTime(),
                request.getAddress()
        );
        appointment.addDoctor(doctor);
        appointment.addPerson(person);
        appointment.addSpeciality(speciality);
        appointmentRepository.save(appointment);
    }

    @Override
    public void deleteAppointment(UUID appointmentId) {
        Appointment appointment = this.getAppointmentById(appointmentId);
        appointmentRepository.delete(appointment);
    }

    @Override
    public List<AppointmentDataResponse> getAppointmentByPersonId(UUID personId) {
        return appointmentRepository.getAppointmentsByPersonId(personId);
    }

    @Override
    public List<AppointmentDataResponse> getAppointmentByDoctorId(UUID doctorId) {
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
