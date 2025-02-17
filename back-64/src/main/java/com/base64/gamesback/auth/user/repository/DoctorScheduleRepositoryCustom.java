package com.base64.gamesback.auth.user.repository;

import com.base64.gamesback.auth.user.dto.DoctorScheduleResponse;

import java.util.List;
import java.util.UUID;

public interface DoctorScheduleRepositoryCustom {

    List<DoctorScheduleResponse> getDoctorScheduleByDoctorId(UUID doctorId);
}
