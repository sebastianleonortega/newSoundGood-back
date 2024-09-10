package com.base64.gamesback.prescription.service.impl;

import com.base64.gamesback.auth.user.entity.Doctor;
import com.base64.gamesback.auth.user.entity.Person;
import com.base64.gamesback.auth.user.repository.DoctorRepository;
import com.base64.gamesback.auth.user.repository.PersonRepository;
import com.base64.gamesback.common.exception_handler.ResourceNotFoundException;
import com.base64.gamesback.medicines.entity.Medicine;
import com.base64.gamesback.medicines.repository.MedicineRepository;
import com.base64.gamesback.prescription.dto.PrescriptionDto;
import com.base64.gamesback.prescription.entity.Prescription;
import com.base64.gamesback.prescription.repository.PrescriptionRepository;
import com.base64.gamesback.prescription.service.PrescriptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class PrescriptionServiceImpl implements PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final MedicineRepository medicineRepository;
    private final PersonRepository personRepository;
    private final DoctorRepository doctorRepository;

    public PrescriptionServiceImpl(PrescriptionRepository prescriptionRepository, MedicineRepository medicineRepository, PersonRepository personRepository, DoctorRepository doctorRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.medicineRepository = medicineRepository;
        this.personRepository = personRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public void createPrescription(PrescriptionDto prescriptionDto) {

        Set<Medicine> medicines = medicineRepository.getMedicinesByMedicineIdIn(List.of(prescriptionDto.getMedicines()));
        Person person = personRepository.findById(prescriptionDto.getPersonId()).orElseThrow(() -> new ResourceNotFoundException("El paciente buscado no existe."));
        Doctor doctor = doctorRepository.findById(prescriptionDto.getDoctorId()).orElseThrow(() -> new ResourceNotFoundException("El doctor buscado no existe."));

        if (medicines.isEmpty()) {
            throw new IllegalArgumentException("Las medicinas ingresadas no existen");
        }

        Prescription prescription = Prescription.create(prescriptionDto.getDescription(), prescriptionDto.getRecommendation());

        prescription.addPerson(person);
        prescription.addDoctor(doctor);
        prescription.addMedicines(medicines);

        prescriptionRepository.save(prescription);
    }
}
