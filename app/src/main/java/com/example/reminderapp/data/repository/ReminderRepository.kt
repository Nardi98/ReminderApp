package com.example.reminderapp.data.repository

import com.example.reminderapp.data.Reminder
import com.example.reminderapp.data.room.ReminderDao
import kotlinx.coroutines.flow.Flow

class ReminderRepository(private val reminderDao: ReminderDao) {

	fun reminders(): Flow<List<Reminder>> = reminderDao.reminders()
	suspend fun getReminderById(reminderId: Long): Reminder? = reminderDao.getReminderById(reminderId)
	suspend fun addReminder(reminder: Reminder): Long {
		return reminderDao.insert((reminder))
	}
	suspend fun addReminderList(reminders: Collection<Reminder>) = reminderDao.insertAll(reminders)
	suspend fun deleteReminder(reminder: Reminder) = reminderDao.delete(reminder)
}