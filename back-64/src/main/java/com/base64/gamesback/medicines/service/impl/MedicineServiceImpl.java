package com.base64.gamesback.medicines.service.impl;

import com.base64.gamesback.common.exception_handler.ResourceNotFoundException;
import com.base64.gamesback.medicines.dto.MedicineDto;
import com.base64.gamesback.medicines.dto.MedicineResponse;
import com.base64.gamesback.medicines.entity.Medicine;
import com.base64.gamesback.medicines.repository.MedicineRepository;
import com.base64.gamesback.medicines.service.MedicineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;

    public MedicineServiceImpl(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    @Override
    public void registerMedicine(MedicineDto medicineDto) {

        List<Medicine> medicines = medicineRepository.getMedicineByNameContainingIgnoreCase(medicineDto.getMedicineName());
        if (!medicines.isEmpty()) {
            throw new IllegalArgumentException("Ya existe una medicina con ese nombre");
        }

        Medicine medicine = Medicine.create(
                medicineDto.getMedicineName()
        );
        medicineRepository.save(medicine);
    }

    @Override
    public void updateMedicine(MedicineDto medicineDto, UUID medicineId) {
        Medicine medicine = this.getMedicineById(medicineId);
        medicine.updateMedicine(medicineDto.getMedicineName());
        medicineRepository.save(medicine);
    }

    @Override
    public void deleteMedicine(UUID medicineId) {
        Medicine medicine = this.getMedicineById(medicineId);
        medicineRepository.delete(medicine);
    }

    @Override
    public Medicine getMedicineById(UUID medicineId) {
        return medicineRepository.findById(medicineId).orElseThrow(() -> new ResourceNotFoundException("No existe la medicina a buscar"));
    }

    @Override
    public List<MedicineResponse> getAllMedicines() {
        return medicineRepository.getAllMedicines();
    }
}
