package com.base64.gamesback.appointment.service.impl;

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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    private final DoctorService doctorService;

    private final PersonService personService;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, UserRepository userRepository, DoctorService doctorService, PersonService personService) {
        this.appointmentRepository = appointmentRepository;
        this.doctorService = doctorService;
        this.personService = personService;
    }

    @Override
    public void registerAppointment(AppointmentDto request) {
        Doctor doctor = doctorService.getDoctorById(request.getDoctorId());
        Person person = personService.getPersonByID(request.getUserId());

        Appointment appointment = Appointment.create(
                request.getDate(),
                request.getTime(),
                request.getSpeciality(),
                request.getAddress()
        );
        appointment.addDoctor(doctor);
        appointment.addPerson(person);
        appointmentRepository.save(appointment);

    }

    @Override
    public void DeleteAppointment(UUID uuid) {

        Appointment appointment = appointmentRepository.findById(uuid).orElseThrow(() -> new ResourceNotFoundException("No existe una cita con ese id"));
        appointmentRepository.deleteById(uuid);

    }

    @Override
    public List<Appointment> getAppointmentByUser(UUID uuid) {
        return (List<Appointment>) appointmentRepository.getAppointmentsByUserId(uuid);
    }
}
