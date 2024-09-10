package com.base64.gamesback.medicines.repository;

import com.base64.gamesback.medicines.dto.MedicineResponse;
import com.base64.gamesback.medicines.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface MedicineRepository extends JpaRepository<Medicine, UUID> {

    List<Medicine> getMedicineByNameContainingIgnoreCase(String name);

    @Query("SELECT M.medicineId as medicineId, M.name as medicineName FROM Medicine M")
    List<MedicineResponse> getAllMedicines();

    Set<Medicine> getMedicinesByMedicineIdIn(List<UUID> medicinesIds);
}
