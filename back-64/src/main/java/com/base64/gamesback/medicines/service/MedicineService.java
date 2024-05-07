package com.base64.gamesback.medicines.service;

import com.base64.gamesback.medicines.dto.MedicineDto;
import com.base64.gamesback.medicines.dto.MedicineResponse;
import com.base64.gamesback.medicines.entity.Medicine;
import com.base64.gamesback.speciality.dto.SpecialityResponse;

import java.util.List;
import java.util.UUID;

public interface MedicineService {

    void registerMedicine(MedicineDto medicineDto);
    void updateMedicine(MedicineDto medicineDto, UUID medicineId);
    void deleteMedicine(UUID medicineId);
    Medicine getMedicineById(UUID medicineId);
    List<MedicineResponse> getAllMedicines();
}
