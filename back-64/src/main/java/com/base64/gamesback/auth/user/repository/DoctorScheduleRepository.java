package com.base64.gamesback.auth.user.repository;

import com.base64.gamesback.auth.user.entity.Doctor;
import com.base64.gamesback.auth.user.entity.DoctorSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DoctorScheduleRepository extends JpaRepository<DoctorSchedule, UUID>, DoctorScheduleRepositoryCustom {
    List<DoctorSchedule> getDoctorSchedulesByDoctor(Doctor doctor);
}
