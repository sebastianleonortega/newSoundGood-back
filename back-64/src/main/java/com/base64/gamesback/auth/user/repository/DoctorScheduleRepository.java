package com.base64.gamesback.auth.user.repository;

import com.base64.gamesback.auth.user.entity.Doctor;
import com.base64.gamesback.auth.user.entity.DoctorSchedule;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface DoctorScheduleRepository extends JpaRepository<DoctorSchedule, UUID>, DoctorScheduleRepositoryCustom {
    List<DoctorSchedule> getDoctorSchedulesByDoctor(Doctor doctor);

    @Transactional
    @Modifying
    @Query("UPDATE DoctorSchedule ds SET ds.available = false WHERE ds.endDate < :currentDate")
    void markUnavailablePastSchedules(@Param("currentDate") LocalDateTime currentDate);

    Boolean existsDoctorScheduleByStartDateAndEndDateAndDoctor(LocalDateTime startDate, LocalDateTime endDate, Doctor doctor);

}
