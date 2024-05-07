package com.base64.gamesback.reminder.service;

import com.base64.gamesback.reminder.dto.ReminderDto;
import com.base64.gamesback.reminder.dto.ReminderResponse;
import com.base64.gamesback.reminder.dto.ReminderUpdateDto;
import com.base64.gamesback.reminder.entity.Reminder;

import java.util.List;
import java.util.UUID;

public interface ReminderService {

    void registerReminder(ReminderDto reminderDto);
    void updateReminder(ReminderUpdateDto reminderUpdateDto, UUID reminderId);
    void deleteReminder(UUID reminderId);
    Reminder getReminderById(UUID reminderId);
    ReminderResponse getReminderResponseById(UUID reminderId);
    List<ReminderResponse> getAllReminderByPersonId(UUID personId);
}
