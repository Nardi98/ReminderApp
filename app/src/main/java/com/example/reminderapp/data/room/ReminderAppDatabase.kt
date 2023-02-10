package com.example.reminderapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.reminderapp.data.LocalDateConverter
import com.example.reminderapp.data.Reminder

@Database(
		entities = [Reminder::class],
		version = 2,
		exportSchema = false
		 )
@TypeConverters(LocalDateConverter::class/*, LocalTimeConverter::class*/)
abstract class ReminderAppDatabase : RoomDatabase() {
	abstract fun reminderDao(): ReminderDao
}