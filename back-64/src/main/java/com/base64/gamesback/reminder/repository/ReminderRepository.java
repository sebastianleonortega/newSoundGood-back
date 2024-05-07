package com.base64.gamesback.reminder.repository;

import com.base64.gamesback.reminder.dto.ReminderResponse;
import com.base64.gamesback.reminder.entity.Reminder;
import com.base64.gamesback.auth.user.entity.Person;
import com.base64.gamesback.medicines.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ReminderRepository extends JpaRepository<Reminder, UUID> {

    Boolean existsByPersonAndMedicineAndReminderTime(Person person, Medicine medicine, String reminderTime);

    @Query(value = "select r.reminder_id as reminderId, r.frequency as reminderFrecuency, r.reminder_time as reminderTime, m.name as medicineName, p.name as personName, p.last_name as personLastName from main.reminder r inner join main.person p on r.person_id = p.user_id inner join main.medicine m on r.medicine_id = m.medicine_id " +
            "where r.reminder_id = :reminderId", nativeQuery = true)
    ReminderResponse getReminderResponseByReminderId(@Param("reminderId") UUID reminderId);

    @Query(value = "select r.reminder_id as reminderId, r.frequency as reminderFrecuency, r.reminder_time as reminderTime, m.name as medicineName, p.name as personName, p.last_name as personLastName from main.reminder r inner join main.person p on r.person_id = p.user_id inner join main.medicine m on r.medicine_id = m.medicine_id " +
            "where p.user_id = :personId", nativeQuery = true)
    List<ReminderResponse> getAllReminderByPerson(@Param("personId") UUID personId);
}
