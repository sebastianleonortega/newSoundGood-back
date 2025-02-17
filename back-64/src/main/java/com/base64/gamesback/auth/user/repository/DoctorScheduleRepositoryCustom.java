package com.base64.gamesback.auth.user.repository;

import com.base64.gamesback.auth.user.dto.DoctorScheduleResponse;
import com.base64.gamesback.common.criteria.Criteria;

import java.util.List;
import java.util.UUID;

public interface DoctorScheduleRepositoryCustom {

    List<DoctorScheduleResponse> getDoctorScheduleByDoctorId(UUID doctorId, Criteria criteria);
}
