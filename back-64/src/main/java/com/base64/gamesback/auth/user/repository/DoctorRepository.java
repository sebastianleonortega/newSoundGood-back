package com.base64.gamesback.auth.user.repository;

import com.base64.gamesback.auth.user.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DoctorRepository extends JpaRepository<Doctor, UUID> {
}
