package com.base64.gamesback.reminder.service.impl;

import com.base64.gamesback.reminder.dto.ReminderDto;
import com.base64.gamesback.reminder.dto.ReminderResponse;
import com.base64.gamesback.reminder.dto.ReminderUpdateDto;
import com.base64.gamesback.reminder.entity.Reminder;
import com.base64.gamesback.reminder.repository.ReminderRepository;
import com.base64.gamesback.reminder.service.ReminderService;
import com.base64.gamesback.auth.user.entity.Person;
import com.base64.gamesback.auth.user.service.PersonService;
import com.base64.gamesback.common.exception_handler.ResourceNotFoundException;
import com.base64.gamesback.medicines.entity.Medicine;
import com.base64.gamesback.medicines.service.MedicineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ReminderServiceImpl implements ReminderService {

    private final ReminderRepository reminderRepository;
    private final PersonService personService;
    private final MedicineService medicineService;

    public ReminderServiceImpl(ReminderRepository reminderRepository, PersonService personService, MedicineService medicineService) {
        this.reminderRepository = reminderRepository;
        this.personService = personService;
        this.medicineService = medicineService;
    }

    @Override
    public void registerReminder(ReminderDto reminderDto) {
        Person person = personService.getPersonById(reminderDto.getPersonId());
        Medicine medicine = medicineService.getMedicineById(reminderDto.getMedicineId());

        if (Boolean.TRUE.equals(reminderRepository.existsByPersonAndMedicineAndReminderTime(person, medicine, reminderDto.getReminderTime()))) {
            throw new IllegalArgumentException("Ya existe un recordatorio para ti, con el mismo medicamento y hora");
        }

        Reminder reminder = Reminder.create(
                reminderDto.getFrequency(),
                reminderDto.getReminderTime()
        );
        reminder.addPerson(person);
        reminder.addMedicine(medicine);
        reminderRepository.save(reminder);
    }

    @Override
    public void updateReminder(ReminderUpdateDto reminderUpdateDto, UUID reminderId) {
        Reminder reminder = this.getReminderById(reminderId);
        if (Boolean.TRUE.equals(reminderRepository.existsByPersonAndMedicineAndReminderTime(reminder.getPerson(), reminder.getMedicine(), reminderUpdateDto.getReminderTime()))) {
            throw new IllegalArgumentException("Ya existe un recordatorio para ti, con el mismo medicamento y hora");
        }
        reminder.updateReminder(
                reminderUpdateDto.getFrequency(),
                reminderUpdateDto.getReminderTime()
        );
        reminderRepository.save(reminder);
    }

    @Override
    public void deleteReminder(UUID reminderId) {
        Reminder reminder = this.getReminderById(reminderId);
        reminderRepository.delete(reminder);
    }

    @Override
    public Reminder getReminderById(UUID reminderId) {
        return reminderRepository.findById(reminderId).orElseThrow(() -> new ResourceNotFoundException("No existe este recordatorio"));
    }

    @Override
    public ReminderResponse getReminderResponseById(UUID reminderId) {
        return reminderRepository.getReminderResponseByReminderId(reminderId);
    }

    @Override
    public List<ReminderResponse> getAllReminderByPersonId(UUID personId) {
        return reminderRepository.getAllReminderByPerson(personId);
    }

}
