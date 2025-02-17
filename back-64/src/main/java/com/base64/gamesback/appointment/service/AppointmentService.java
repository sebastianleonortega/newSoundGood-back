package com.base64.gamesback.appointment.service;

import com.base64.gamesback.appointment.dto.*;
import com.base64.gamesback.appointment.entity.Appointment;

import java.util.List;
import java.util.UUID;

public interface AppointmentService {


    void registerAppointment(AppointmentRequest appointmentRequest);

    void updateAppointment(AppointmentUpdateRequest appointmentUpdateRequest);

    void deleteAppointment(UUID appointmentId);

    List<AppointmentDataResponsePatient> getAppointmentByPersonId(UUID personId);

    List<AppointmentDataResponseDoctor> getAppointmentByDoctorId(UUID doctorId);

    AppointmentDataResponse getAppointmentByAppointmentId(UUID appointmentId);

    Appointment getAppointmentById(UUID appointmentId);

}
