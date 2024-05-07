package com.base64.gamesback.reminder.entity;

import com.base64.gamesback.auth.user.entity.Person;
import com.base64.gamesback.medicines.entity.Medicine;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "reminder", schema = "main")
@Getter
@NoArgsConstructor
public class Reminder {

    @Id
    @GeneratedValue
    @Column(name = "reminder_id")
    private UUID reminderId;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @ManyToOne
    @JoinColumn(name = "medicine_id", nullable = false)
    private Medicine medicine;

    @Column(nullable = false)
    private String frequency;

    @Column(nullable = false)
    private String reminderTime;

    public Reminder(String frequency, String reminderTime) {
        this.frequency = frequency;
        this.reminderTime = reminderTime;
    }

    public static Reminder create(String frequency, String reminderTime){
        return new Reminder(
          frequency, reminderTime
        );
    }

    public void updateReminder(String frequency, String reminderTime) {
        this.frequency = frequency;
        this.reminderTime = reminderTime;
    }

    public void addPerson(Person person) {
        this.person = person;
    }

    public void addMedicine(Medicine medicine) {
        this.medicine = medicine;
    }
}
