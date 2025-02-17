package com.base64.gamesback.auth.user.repository;

import com.base64.gamesback.auth.user.dto.UserDoctorResponse;
import com.base64.gamesback.auth.user.dto.UserPatientResponse;
import com.base64.gamesback.auth.user.dto.UserResponseDto;
import com.base64.gamesback.common.criteria.Criteria;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface UserRepositoryCustom {

    UserDoctorResponse getUserDoctorById(UUID userId);

    UserPatientResponse getUserPatientById(UUID userId);

    List<UserDoctorResponse> getAllUserDoctors(UUID specialityId);

    Page<UserDoctorResponse> getAllUserDoctorsPage(Criteria criteria, Long totalRows);

    Long countUserDoctorsPage(Criteria criteria);

    Page<UserResponseDto> getAllUsersPage(Criteria criteria, Long totalRows);

    Long countUsersPage(Criteria criteria);

    List<UserPatientResponse> getAllUserPatients();

    Page<UserPatientResponse> getAllUserPatientsPage(Criteria criteria, Long totalRows);

    Long countUserPatientsPage(Criteria criteria);
}
