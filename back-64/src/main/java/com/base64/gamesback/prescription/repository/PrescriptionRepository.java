package com.base64.gamesback.prescription.repository;

import com.base64.gamesback.prescription.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PrescriptionRepository extends JpaRepository<Prescription, UUID>, PrescriptionRepositoryCustom {
}
