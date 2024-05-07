package com.base64.gamesback.appointment.service;

import com.base64.gamesback.appointment.dto.AppointmentDto;
import com.base64.gamesback.appointment.entity.Appointment;

import java.util.List;
import java.util.UUID;

public interface AppointmentService {


    void registerAppointment(AppointmentDto appointmentDto);

    void DeleteAppointment(UUID uuid);

    List<Appointment> getAppointmentByUser(UUID uuid);

}
