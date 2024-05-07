package com.base64.gamesback.appointment.service;

import com.base64.gamesback.appointment.dto.AppointmentDataResponse;
import com.base64.gamesback.appointment.dto.AppointmentDto;
import com.base64.gamesback.appointment.entity.Appointment;

import java.util.List;
import java.util.UUID;

public interface AppointmentService {


    void registerAppointment(AppointmentDto appointmentDto);

    void deleteAppointment(UUID appointmentId);

    List<AppointmentDataResponse> getAppointmentByPersonId(UUID personId);

    List<AppointmentDataResponse> getAppointmentByDoctorId(UUID doctorId);

    AppointmentDataResponse getAppointmentByAppointmentId(UUID appointmentId);

    Appointment getAppointmentById(UUID appointmentId);

}
