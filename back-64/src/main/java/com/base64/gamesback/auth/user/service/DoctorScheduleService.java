package com.base64.gamesback.auth.user.service;

import com.base64.gamesback.auth.user.dto.DoctorScheduleRequest;
import com.base64.gamesback.auth.user.dto.DoctorScheduleResponse;
import com.base64.gamesback.auth.user.dto.DoctorScheduleUpdateRequest;
import com.base64.gamesback.auth.user.entity.DoctorSchedule;
import com.base64.gamesback.common.object.SearchByCriteria;

import java.util.List;
import java.util.UUID;

public interface DoctorScheduleService {

    void registerDoctorSchedule(UUID doctorId, DoctorScheduleRequest doctorScheduleRequest);

    void updateDoctorSchedule(DoctorScheduleUpdateRequest doctorScheduleUpdateRequest);

    DoctorSchedule getDoctorScheduleById(UUID doctorScheduleId);

    List<DoctorScheduleResponse> getDoctorScheduleByDoctorId(UUID doctorId, SearchByCriteria search);
}
